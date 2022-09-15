package ydzhao.weixin.tuisong.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>
 * 多线程执行定时器
 * </p>
 *
 * @author pengao
 * @since 2022-08-25
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	/**
	 * 核心线程数
	 */
	@Value("${scheduled.corePoolSize}")
	private int corePoolSize;

	/**
	 * 最大线程数
	 */
	@Value("${scheduled.maxPoolSize}")
	private int maxPoolSize;

	/**
	 * 任务队列容量
	 */
	@Value("${scheduled.queueCapacity}")
	private int queueCapacity;

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}
}
