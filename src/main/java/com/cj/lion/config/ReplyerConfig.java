package com.cj.lion.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cj.config.AppProperties;
import com.cj.domain.sent.TextSentContent;
import com.cj.lion.replyer.ActivityReplyer;
import com.cj.replyer.LastReplyer;
import com.cj.replyer.MessageSender;
import com.cj.replyer.NewsPropertiesReplyer;
import com.cj.replyer.ReplyerChain;
import com.cj.replyer.SubscribeReplyer;
import com.cj.replyer.TextPropertiesReplyer;

@Configuration
public class ReplyerConfig {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public LastReplyer lastReplyer() {
		LastReplyer lastReplyer = new LastReplyer();
		TextSentContent sentContent = new TextSentContent();
		sentContent.setContent("终于等到您啦～小细将为您提供贴心服务～  输入“小细”，查看更多详情～～");
		lastReplyer.setSentContent(sentContent);
		return lastReplyer;
	}

	@Bean
	public MessageSender messageSender(ReplyerChain replyerChain) {
		MessageSender messageSender = new MessageSender();
		messageSender.setReplyerChain(replyerChain);
		messageSender.setWechatId(appProperties.getWecahtId());
		return messageSender;
	}

	@Bean
	public NewsPropertiesReplyer newsPropertiesReplyer()
			throws FileNotFoundException, IOException {
		NewsPropertiesReplyer propertiesReplyer = new NewsPropertiesReplyer();
		return propertiesReplyer;
	}

	@Bean
	public ReplyerChain replyerChain(
			NewsPropertiesReplyer newsPropertiesReplyer,
			TextPropertiesReplyer textPropertiesReplyer,
			SubscribeReplyer subscribeReplyer, LastReplyer lastReplyer) {
		ReplyerChain replyerChain = new ReplyerChain();

		ActivityReplyer activityReplyer = new ActivityReplyer(appProperties);
		replyerChain.addCustomReplyer(activityReplyer);

		replyerChain.setNewsPropertiesReplyer(newsPropertiesReplyer);
		replyerChain.setTextPropertiesReplyer(textPropertiesReplyer);
		replyerChain.setSubscribeReplyer(subscribeReplyer);
		replyerChain.setLastReplyer(lastReplyer);
		return replyerChain;
	}

	@Bean
	public SubscribeReplyer subscribeReplyer() {
		SubscribeReplyer sbscribeReplyer = new SubscribeReplyer();
		TextSentContent sentContent = new TextSentContent();
		sentContent
				.setContent("Hello亲～我是小细～ Systema细齿洁是狮王旗下专业的牙龈护理品牌,为您带来最细致入微的关怀呵护。感谢您关注小细！小细会定期发送专属的免费试用、新品信息、促销活动及牙龈健康小贴士！回复“测试”，开启牙齿健康小诊断，了解你的牙齿问题！还有更多功能等你来发现！");
		sbscribeReplyer.setSentContent(sentContent);
		return sbscribeReplyer;
	}

	@Bean
	public TextPropertiesReplyer textPropertiesReplyer() {
		TextPropertiesReplyer propertiesReplyer = new TextPropertiesReplyer();
		return propertiesReplyer;
	}
}
