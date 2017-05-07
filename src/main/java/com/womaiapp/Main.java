package com.womaiapp;

import com.womaiapp.util.JacksonUtil;
import org.apache.commons.cli.*;

/**
 * 程序入口
 * Created by wuchaofei on 2017/5/7.
 */
public class Main {
    private Config config;

    public static void main(String[] args) {
        Main main=new Main();
        main.init(args);

    }

    private void init(String[] args) {
        try {
            parseCommand(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseCommand(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help",false,"help information");
        Option c = Option.builder("c")  //option的名字,判断的时候要使用这个名字
                .required(false)               //是否必须有这个选项
                .hasArg()                         //带一个参数
                .argName("filename")     //参数的名字
                .desc("config file path")  //描述
                .build();                             //必须有
        options.addOption(c);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options,args);
        //将除了选项之外的参数打印出来 otherfilename
//        String[] s = cmd.getArgs();
        //询问是否有help
        if(cmd.hasOption("help")) {
            //调用默认的help函数打印
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "-c <文件路径>", options );
            return;
        }
        String filename = null;
        //配置文件路径
        if(cmd.hasOption("c")){
            filename = cmd.getOptionValue("c");
        }else{
            filename = JacksonUtil.class.getClassLoader().getResource("config.json").getPath();
        }

        if(filename!=null){
            loadConfig(filename);
        }
    }

    private void loadConfig(String filename) throws ParseException {
        this.config = JacksonUtil.loadConfig(filename);
    }

}
