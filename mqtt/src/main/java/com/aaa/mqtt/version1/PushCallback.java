package com.aaa.mqtt.version1;

/**
 * @author liuzhen.tian
 * @version 1.0 PushCallback.java  2021/5/14 21:29
 */

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PushCallback implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        long reconnectTimes = 1;
        while (true) {
            try {
                if (MqttPushClient.getClient().isConnected()) {
                    log.warn("mqtt reconnect success end");
                    return;
                }
                log.warn("mqtt reconnect times = {} try again...", reconnectTimes++);
                MqttPushClient.getClient().reconnect();
            } catch (MqttException e) {
                log.error("", e);
            }
            try {
                // 每隔三秒重试，时间依次增加
                TimeUnit.SECONDS.sleep(3 + reconnectTimes * 5);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }

    }

    /**
     * 消息到达后
     * subscribe后，执行的回调函数
     *
     * @param topic       消息主题
     * @param mqttMessage 消息
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        log.info("接收消息主题 : " + topic);
        log.info("接收消息Qos : " + mqttMessage.getQos());
        String message = new String(mqttMessage.getPayload());
        log.info("接收消息内容 : " + message);

        /**
         * 返回数据格式
         * {"DevSn":"1212012432","TimeStamp":"1610416762"
         * ,"RecordType":1,"CardNum":15736758729,"IsVaild":1,"RecordTime":"2021/5/18 13:25:30"
         * ,"order":"202105181125052696126352"}
         */
        try {
            // 执行相关业务逻辑,注意此处务必扑捉异常，否则会丢失连接
        } catch (Exception e) {
            log.error("mqtt 监听topic数据异常", e);
        }

    }

    /**
     * publish后，配送完成后回调的方法
     *
     * @param iMqttDeliveryToken
     */

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
