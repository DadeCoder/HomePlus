package com.dade.rpcapi.dto.chat;



import com.dade.rpcapi.enums.MessageContentAttachKeyEnum;
import com.dade.rpcapi.enums.MessageContentType;

import java.util.Date;
import java.util.List;

/**
 * 创建消息内容DTO
 * Created by Dade on 2017/1/10.
 */
public final class MessageContentDtoFactory
{

    /**
     * 生成运营消息Dto
     * @param theme       运营消息主题
     * @param content     运营消息内容
     * @param url         运营消息URL
     * @return
     */
    public static MessageContentDto createOperateMessageDto(String theme, String content, String url)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        messageContentDto.setTheme(theme);
        messageContentDto.setContent(content);
        messageContentDto.setUrl(url);

        messageContentDto.setType(MessageContentType.OPERATE_MESSAGE.code());

        return messageContentDto;
    }

    /**
     * 生成审核客户消息Dto
     * @param accountCompanyId
     * @param accountCompanyName
     * @return
     */
    public static MessageContentDto createProcessAccountCompanyMessageDto(
            String accountCompanyId,
            String accountCompanyName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        messageContentDto.setAccountCompanyId(accountCompanyId);
        messageContentDto.setAccountCompanyName(accountCompanyName);

        messageContentDto.setType(MessageContentType.PROCESS_ACCOUNT_COMPANY.code());

        return messageContentDto;
    }

    /**
     * 生成应聘候选者消息Dto
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @param candidateName
     * @param candidateMobile
     * @return
     */
    public static MessageContentDto createShareWechatPositionCandidateDto(
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName,
            String candidateName,
            String candidateMobile)
    {
        MessageContentDto messageContentDto = new MessageContentDto();
        messageContentDto.setAccountCompanyId(accountCompanyId);
        messageContentDto.setAccountCompanyName(accountCompanyName);

        messageContentDto.setType(MessageContentType.SHARE_WECHAT_POSITION_CANDIDATE.code());
        messageContentDto.setPositionId(positionId);
        messageContentDto.setPositionName(positionName);
        messageContentDto.setCandidateName(candidateName);
        messageContentDto.setCandidateMobile(candidateMobile);
        return messageContentDto;
    }

    /**
     * 生成授权新客户消息Dto
     * @param accountCompanyId
     * @param accountCompanyName
     * @return
     */
    public static MessageContentDto createAuthNewAccountCompanyDto(
            String accountCompanyId,
            String accountCompanyName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        messageContentDto.setAccountCompanyId(accountCompanyId);
        messageContentDto.setAccountCompanyName(accountCompanyName);

        messageContentDto.setType(MessageContentType.AUTH_NEW_ACCOUNT_COMPANY.code());

        return messageContentDto;
    }

    /**
     * 生成推荐候选人消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @return
     */
    public static MessageContentDto createRecommendNewRecommenderDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_NEW_RECOMMENDER.code());

        return messageContentDto;
    }

    /**
     * 生成接受候选人消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @return
     */
    public static MessageContentDto createRecommendOperationReceiveDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_RECEIVE.code());

        return messageContentDto;
    }

    /**
     * 推荐到企业通知消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @return
     */
    public static MessageContentDto createRecommendOperationRecommendDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_RECOMMEND.code());

        return messageContentDto;
    }

    /**
     * 安排面试消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @param interviewDate
     * @param interviewAddress
     * @param interviewRemark
     * @param interviewer
     * @param InterviewType
     * @param InterviewTypeInfo
     * @return
     */
    public static MessageContentDto createRecommendOperationInterViewDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName,
            Date interviewDate,
            String interviewAddress,
            String interviewRemark,
            String interviewer,
            Integer InterviewType,
            String InterviewTypeInfo)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_INTERVIEW.code());

        messageContentDto.setInterviewDate(interviewDate);
        messageContentDto.setInterviewAddress(interviewAddress);
        messageContentDto.setInterviewRemark(interviewRemark);
        messageContentDto.setInterviewer(interviewer);
        messageContentDto.setInterviewType(InterviewType);
        messageContentDto.setInterviewTypeInfo(InterviewTypeInfo);

        return messageContentDto;
    }

    /**
     * 发放OFFER通知消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @param offerSalary
     * @param offerRevenue
     * @param offerGuarantee
     * @param offerPay
     * @param offerPercent
     * @param onBoardDate
     * @param cdnKey
     * @param cdnUrl
     * @param localName
     * @return
     */
    public static MessageContentDto createRecommendOperationOfferDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName,
            Integer offerSalary,
            Double offerRevenue,
            String offerGuarantee,
            Integer offerPay,
            List<Integer> offerPercent,
            Date onBoardDate,
            String cdnKey,
            String cdnUrl,
            String localName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_OFFER.code());

        messageContentDto.setOfferSalary(offerSalary);
        messageContentDto.setOfferRevenue(offerRevenue);
        messageContentDto.setOfferGuarantee(offerGuarantee);
        messageContentDto.setOfferPay(offerPay);
        messageContentDto.setOfferPercent(offerPercent);
        messageContentDto.setOnBoardDate(onBoardDate);

        messageContentDto.setCdnKey(cdnKey);
        messageContentDto.setCdnUrl(cdnUrl);
        messageContentDto.setLocalName(localName);

        return messageContentDto;
    }

    /**
     * 入职通知消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @return
     */
    public static MessageContentDto createRecommendOperationInductionDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName,
            Date inductionDate,
            Date expiryDate)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_INDUCTION.code());

        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.INDUCTION_DATE, inductionDate);
        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.EXPIRY_DATE, expiryDate);

        return messageContentDto;
    }

    /**
     * 重新激活通知消息Dto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     * @return
     */
    public static MessageContentDto createRecommendOperationReactivateDto(
            String recommenderId,
            String candidateId,
            String candidateName,
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        _initRcapData(messageContentDto,
                recommenderId, candidateId, candidateName,
                accountCompanyId, accountCompanyName,
                positionId, positionName);

        messageContentDto.setType(MessageContentType.RECOMMEND_OPERATION_REACTIVATE.code());

        return messageContentDto;
    }


    /**
     * 创建职位匹配顾问-推荐职位给顾问Dto
     * @param accountCompanyId      客户公司ID
     * @param accountCompanyName    客户公司名称
     * @param positionId            职位ID
     * @param positionName          职位名称
     * @param minRevenue            最小收益
     * @param maxRevenue            最大收益
     * @param minSalary             最小年薪
     * @param maxSalary             最大年薪
     * @return
     */
    public static MessageContentDto createRecommendPositionToUserDto(
            String accountCompanyId,
            String accountCompanyName,
            String positionId,
            String positionName,
            double minRevenue,
            double maxRevenue,
            double minSalary,
            double maxSalary)
    {
        MessageContentDto messageContentDto = new MessageContentDto();

        messageContentDto.setAccountCompanyId(accountCompanyId);
        messageContentDto.setAccountCompanyName(accountCompanyName);
        messageContentDto.setPositionId(positionId);
        messageContentDto.setPositionName(positionName);

        // 收益,年薪添加到附加对象中
        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.MIN_REVENUE, minRevenue);
        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.MAX_REVENUE, maxRevenue);
        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.MIN_SALARY, minSalary);
        messageContentDto.putAttachObject(MessageContentAttachKeyEnum.MAX_SALARY, maxSalary);

        messageContentDto.setType(MessageContentType.RECOMMEND_POSITION_TO_USER.code());

        return messageContentDto;
    }

    /**
     * 初始化 推荐记录, 候选人, 客户公司, 职位信息 信息
     * @param messageContentDto
     * @param recommenderId
     * @param candidateId
     * @param candidateName
     * @param accountCompanyId
     * @param accountCompanyName
     * @param positionId
     * @param positionName
     */
    private static void _initRcapData(MessageContentDto messageContentDto,
                                      String recommenderId,
                                      String candidateId,
                                      String candidateName,
                                      String accountCompanyId,
                                      String accountCompanyName,
                                      String positionId,
                                      String positionName)
    {
        messageContentDto.setRecommenderId(recommenderId);

        messageContentDto.setCandidateId(candidateId);
        messageContentDto.setCandidateName(candidateName);

        messageContentDto.setAccountCompanyId(accountCompanyId);
        messageContentDto.setAccountCompanyName(accountCompanyName);

        messageContentDto.setPositionId(positionId);
        messageContentDto.setPositionName(positionName);
    }

}
