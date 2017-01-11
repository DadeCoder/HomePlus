/**
 * index demo
 * Created by dade on 2016/1/10
 */

var socket;

// 路由对象
var router = {};

/**
 * 路由-登录推送
 * @param dto
 * @constructor
 */

router.CHAT_MESSAGE = function (dto)
{
    console.log("router CHAT_MESSAGE begin " + dto);
    var msgId = dto.id;
    var senderName = dto.sender.name;
    var sendDate = dto.sendDate;
    var content = dto.content.content;

    console.log("router.CHAT_MESSAGE init date"
        + msgId + " "
        + senderName + " "
        + sendDate + " "
        + content + " ");

    output('#chat_friend_console',
        '<span class="username-msg">' + sendDate +' : '+ senderName + ':</span> ' + content + " ==> " + msgId);

   console.log("router.CHAT_MESSAGE output end");


}




/*router.CHAT_WORLD_TEXT = function (dto)
{
    output('#chat_world_console', '<span class="username-msg">' + dto.senderName + ':</span> ' + dto.content);
}*/

console.log("my name is dade");

/**
 * 启动连接
 */
function sendConnect()
{

    console.log("connecting");

    if (socket != null)
    {
        console.log(moment().format('HH:mm:ss.SSS') + ": socket connected !!");
        return;
    }

    var _socket = io.connect('http://localhost:9092');

    // 连接事件
    _socket.on('connect', function() {  
        output('#notice_console', '<span class="connect-msg">Client has connected to the server!</span>');
        console.log("Connected!");
    });

    // 移除连接事件
    _socket.on('disconnect', function() {
        output('#notice_console', '<span class="disconnect-msg">The client has disconnected!</span>');
        console.log("Disconnected!");
    });

    // 收包事件
    _socket.on('sendPacket', function(data)
    {
        var mapping = data.mapping;
        var dto     = data.dto;
        var routerFunction = router[mapping];
        routerFunction(dto);

        //output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
    });

    socket = _socket;
}


/******************** 页面操作方法 ********************/

/**
 * 关闭连接
 */
function sendDisconnect()
{
    if (!checkConnection())
        return;

    socket.disconnect();
    socket = null;
}

/**
 * 登录
 */
function login()
{

    console.log("logging");

    if (!checkConnection())
        return;

    var user    = $('#user').val();
    var object  = {mapping: "LOGIN", jsonData: JSON.stringify({userId:user, password:'123456'})};
    socket.emit('json', object, function(data)
    {
        var errorCode   = data.errorCode;
        if (errorCode != 0)
        {
            alert ("LOGIN callback error :" + errorCode);
            return;
        }

        var name = data.response.user;
        var html = '<span class="username-msg">' + name + ':</span> 登录!!';
        output('#notice_console', html);
    });
}

/**
 * 登出
 */
function loginout()
{
    if (!checkConnection())
        return;

    alert("TODO loginout");
}

/**
 * 发送聊天信息
 */
function chatSendMessage ()
{
    if (!checkConnection())
        return;

    var friend  = $('#chat_send_user_id').val();
    var msg     = $('#chat_send_user_msg').val();

    var content = {type:1, content: msg};
    var object  = {mapping: "CHAT_SEND_MESSAGE", jsonData: JSON.stringify({receiverId:friend, message:content})};
    socket.emit('json', object, function(data)
    {
        var errorCode   = data.errorCode;
        if (errorCode != 0)
        {
            alert ("CHAT_SEND_MESSAGE callback error :" + errorCode);
            return;
        }
    });


    $('#chat_send_user_msg').val('');
    var html = '<span class="username-msg">' + msg + ':</span>';
    //output('#chat_friend_console', html);
    
    
}

/**
 * 聊天获得好友列表
 */
function chatGetUserList ()
{
    if (!checkConnection())
        return;

    var object = {mapping: "CHAT_GET_USER_LIST", jsonData: JSON.stringify({})};

    socket.emit('json', object, function(data)
    {
        var errorCode   = data.errorCode;
        if (errorCode != 0)
        {
            alert ("CHAT_GET_USER_LIST callback error :" + errorCode);
            return;
        }

        var html = "CHAT_GET_USER_LIST";
        var chatUserList = data.response.list;
        for (var i=0; i<chatUserList.length; i++)
        {
            var chatUser = chatUserList[i];
            console.log(chatUser);
            html += ("<br/>"
                + '&nbsp;&nbsp;<span class="username-msg">' + chatUser.user.nick + '</span> '
                + chatUser.user.userId + "- "
                + chatUser.user.userType + " - "
                + chatUser.unreadCount + " - "
                + chatUser.lastMessageDate);
        }

        output('#chat_info_console', html);
    });
}


/**
 * 聊天获得指定用户消息列表
 */
function chatGetUserMessageList ()
{
    if (!checkConnection())
        return;

    var chatUserId  = $('#chat_user_id').val();
    var object = {mapping: "CHAT_GET_USER_MESSAGE_LIST", jsonData: JSON.stringify({userId:chatUserId, messageId:"", count: 10})};

    socket.emit('json', object, function(data)
    {
        var errorCode   = data.errorCode;
        if (errorCode != 0)
        {
            alert ("CHAT_GET_USER_MESSAGE_LIST callback error :" + errorCode);
            return;
        }

        var html = "CHAT_GET_USER_MESSAGE_LIST";
        var messageList = data.response.list;
        for (var i=0; i<messageList.length; i++)
        {
            var message = messageList[i];
            console.log(chatUser);
            html += ("<br/>"
            + '&nbsp;&nbsp;<span class="username-msg">' + message.content.content + " - " + message.content.type + '</span> '
            + message.sender.nick + " - "
            + message.receiver.nick + " - "
            + message.sendDate + " - "
            + message.id);
        }

        output('#chat_info_console', html);
    });
}

/******************** 工具方法 ********************/

/**
 * 工具输出方法
 * @param node
 * @param message
 */
function output(node, message) {
    var currentTime = "<span class='time'>" +  moment().format('HH:mm:ss.SSS') + "</span>";
    var element = $("<div>" + currentTime + " " + message + "</div>");
    $(node).prepend(element);
}

/**
 * 检测连接是否开启
 * @returns {boolean}
 */
function checkConnection ()
{
    if (socket == null)
    {
        console.log(moment().format('HH:mm:ss.SSS') + ": socket connection closed or connection not open !!");
        return false;
    }

    return true;
}