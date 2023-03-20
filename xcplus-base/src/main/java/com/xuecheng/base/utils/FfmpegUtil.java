package com.xuecheng.base.utils;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;
import java.net.URL;

public class FfmpegUtil {
    /**
     * 视频格式化为mp4
     *
     * @param url
     * @param targetPath
     * @return
     */
    public static boolean formatToMp4(String url, String targetPath) throws Exception{
        File target = new File(targetPath);
        MultimediaObject multimediaObject;

        // 若是本地文件： multimediaObject = new MultimediaObject(new File("你的本地路径"));
        multimediaObject = new MultimediaObject(new File(url));
        EncodingAttributes attributes = new EncodingAttributes();
        // 设置视频的音频参数
        AudioAttributes audioAttributes = new AudioAttributes();
        attributes.setAudioAttributes(audioAttributes);
        audioAttributes.setCodec("aac");
        audioAttributes.setChannels(2);
        // 设置视频的视频参数
        VideoAttributes videoAttributes = new VideoAttributes();
        videoAttributes.setCodec("h264");
        // 设置帧率
        videoAttributes.setFrameRate(25);
        attributes.setVideoAttributes(videoAttributes);
        // 设置输出格式
        attributes.setInputFormat("avi");
        attributes.setOutputFormat("mp4");
        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, attributes);
        return true;
    }


    /**
     * 视频转码
     * @param videoSource
     * @param videoTarget
     * @return true or false
     */
    public static boolean videoToVideo(String videoSource, String videoTarget) {
//        Date time = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        System.out.println(simpleDateFormat.format(time));

        long start = System.currentTimeMillis();

        File source = new File(videoSource);
        File target = new File(videoTarget);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        audio.setBitRate(236000 / 2);
        audio.setChannels(2);
        audio.setSamplingRate(8000);

        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setBitRate(1000000);
        video.setFrameRate(25);
        video.setQuality(4);
//        video.setSize(new VideoSize(720, 480));

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);


        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source), target, attrs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(encoder.getUnhandledMessages());
            return false;
        }finally {
//            time = new Date();
//            System.out.println(simpleDateFormat.format(time));
            long end = System.currentTimeMillis();
            System.out.println("总耗时："+ (end-start) +"ms");
        }
    }
}
