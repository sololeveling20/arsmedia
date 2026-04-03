package dev.razafindratelo.arsmedia.config;

import dev.razafindratelo.arsmedia.InfraGenerated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Scheduled keep-alive task to prevent Render free tier from hibernating.
 *
 * <p>Pings the application's own health endpoint every 9 minutes to keep the service active.
 * Render free tier services hibernate after 15 minutes of inactivity.
 */
@InfraGenerated
@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "render.keepalive.enabled", havingValue = "true", matchIfMissing = true)
public class RenderKeepAliveScheduler {

  private final RestTemplate restTemplate;

  /**
   * Pings the local health endpoint every 9 minutes to prevent hibernation.
   *
   * <p>Render free tier hibernates after 15 minutes of inactivity. This task runs every 9 minutes
   * to ensure the service stays awake.
   */
  @Scheduled(fixedRateString = "540000") // 9 minutes in milliseconds
  public void keepAlive() {
    try {
      ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/ping", String.class);
      if (response.getStatusCode().value() == 200) {
        log.debug("Keep-alive ping successful");
      }
    } catch (Exception e) {
      log.warn("Keep-alive ping failed: {}", e.getMessage());
    }
  }
}
