package com.hj.test;


import com.google.common.collect.Maps;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * smack框架测试通信，包括以下几点：
 * 1.创建账户
 * 2.管理账户
 * 3.测试发送消息和接收消息
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/28  23:03
 */
public class SmackXMPPTest {

    private Connection connection;
    private ConnectionConfiguration config;
    private final static String server = "121.41.29.72";
    private final static String serverJID = "iz23m8u2jf9z";
    private final static String conferenceServerJID="@conference.iz23m8u2jf9z";
    private final static int port = 5222;


    Logger logger = LoggerFactory.getLogger(SmackXMPPTest.class);


    public void init() {
        /**
         * 端口5222：客户端使用标准端口连接到服务器，连接可能没有加密（可以手动在控制台设置）
         */
        config = new ConnectionConfiguration(server, port);
        //启用压缩
        config.setCompressionEnabled(true);
        //启用SASL安全验证
        config.setSASLAuthenticationEnabled(true);
        //启用debug
        config.setDebuggerEnabled(false);


        try {
            /**
             * 创建xmpp连接
             */
            connection = new XMPPConnection(config);
            /**
             * 连接启动
             */
            connection.connect();
        }
        catch (Exception e) {
            logger.error("create xmpp connect fail: ", e);
        }

    }

    public void destroy() {
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }

    /**
     * 用户管理操作，创建用户，修改密码等
     */
    public void handleAccountManager() {
        AccountManager accountManager = connection.getAccountManager();
        System.out.println("是否支持用户创建:\n" + accountManager.supportsAccountCreation());

        /**
         * 设置可选属性
         */
        Map<String, String> map = Maps.newHashMap();
        map.put("email", "editice1@gmail.com");
        map.put("name", "张三三");
        try {
            accountManager.createAccount("zj2", "1223", map);
//            accountManager.changePassword("4526");
            System.out.println("创建用户成功");
        }
        catch (Exception e) {
            logger.error("create account fail: ", e);
            e.printStackTrace();
        }
    }

    /**
     * 用户登陆，获取用户好友，用户组等信息
     */
    public void handleUserLogin() {
        try {
            connection.login("zj2", "1223");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("login user: " + connection.getUser());
        /** 所有用户组 */
        Roster roster = connection.getRoster();

        /** 好友用户组，你可以用Spark添加用户好友，这样这里就可以查询到相关的数据 */
        Collection<RosterEntry> rosterEntiry = roster.getEntries();
        Iterator<RosterEntry> iter = rosterEntiry.iterator();
        while (iter.hasNext()) {
            RosterEntry entry = iter.next();
            System.out.println(entry);
        }

        System.out.println("-------------------------------");
        /** 未处理、验证好友，添加过的好友，没有得到对方同意 */
        Collection<RosterEntry> unfiledEntries = roster.getUnfiledEntries();
        iter = unfiledEntries.iterator();
        while (iter.hasNext()) {
            RosterEntry entry = iter.next();
            System.out.println(entry);
        }
    }

    /**
     * 测试聊天消息管理类
     */
    public void handleChatManager() throws XMPPException {
        try {
            connection.login("zj", "123");
            System.out.println("login succssfull");
        }
        catch (XMPPException e) {
            e.printStackTrace();
        }

        /** 设置状态 */
        Presence presence = new Presence(Presence.Type.available);
        presence.setStatus("Q我吧");
        connection.sendPacket(presence);

        /** 获取当前登陆用户的聊天管理器 */
        ChatManager chatManager = connection.getChatManager();
        /** 为指定用户创建一个chat，MyMessageListeners用于监听对方发过来的消息  */
        Chat chat = chatManager.createChat("zj2@" + serverJID, new MyMessageListeners());
        /** 发送消息 */
        chat.sendMessage("h!~ 你妹 ……");

        /** 用message对象发送消息 */
        Message message = new Message();
        message.setBody("message");
        message.setProperty("color", "red");
        chat.sendMessage(message);
        System.out.println("发送消息成功");
        try {
            Thread.sleep(5000*20);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义消息监听器，用户监听对方发送的消息，也可以向对方发送消息
     */
    class MyMessageListeners implements MessageListener {
        public void processMessage(Chat chat, Message message) {
            try {
                /**
                 * 用户当前的聊天管理器实例为chat
                 * 用户接收到的信息主体即为message
                 */

                /** 向用户发送自己接收到的消息 */
                chat.sendMessage("哥接收到的消息是……" + message.getBody());
            }
            catch (XMPPException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试创建多人聊天室
     */
    public void createChatRoom() throws XMPPException {
        try {
            connection.login("zj", "123");
            System.out.println("login succssfull");
        }
        catch (XMPPException e) {
            e.printStackTrace();
        }
        MultiUserChat muc = new MultiUserChat(connection, "roomName@"+conferenceServerJID);
        // 创建聊天室
        muc.create("room nick 1");
        // 获得聊天室的配置表单
        Form form = muc.getConfigurationForm();
        // 根据原始表单创建一个要提交的新表单。
        Form submitForm = form.createAnswerForm();
        // 向要提交的表单添加默认答复
        for (Iterator fields = form.getFields(); fields.hasNext();) {
            FormField field = (FormField) fields.next();
            if (!FormField.TYPE_HIDDEN.equals(field.getType())
                && field.getVariable() != null) {
                // 设置默认值作为答复
                submitForm.setDefaultAnswer(field.getVariable());
            }
        }
        // 设置聊天室的新拥有者
        // 设置聊天室是持久聊天室，即将要被保存下来
        submitForm.setAnswer("muc#roomconfig_persistentroom", true);
        // 房间仅对成员开放
        // 进入是否需要密码
        //submitForm.setAnswer("muc#roomconfig_passwordprotectedroom", true);
        // 设置进入密码
        //submitForm.setAnswer("muc#roomconfig_roomsecret", "password");
        submitForm.setAnswer("muc#roomconfig_membersonly", false);
        // 允许占有者邀请其他人
        submitForm.setAnswer("muc#roomconfig_allowinvites", true);
        // 能够发现占有者真实 JID 的角色
        // submitForm.setAnswer("muc#roomconfig_whois", "anyone");
        // 登录房间对话
        submitForm.setAnswer("muc#roomconfig_enablelogging", true);
        // 仅允许注册的昵称登录
        submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
        // 允许使用者修改昵称
        submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
        // 允许用户注册房间
        submitForm.setAnswer("x-muc#roomconfig_registration", false);
        // 发送已完成的表单（有默认值）到服务器来配置聊天室
        muc.sendConfigurationForm(submitForm);
    }

    /**
     * 测试加入房间
     */
    public void joinChatRoom() throws XMPPException {
        try {
            connection.login("zj2", "1223");
            System.out.println("login succssfull");
        }
        catch (XMPPException e) {
            e.printStackTrace();
        }
        MultiUserChat muc = new MultiUserChat(connection, "roomname"+conferenceServerJID);
        muc.join("zj2","123456");
        System.out.println("join successful");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 在聊天室聊天
     */
    public void communicateChatRoom() throws XMPPException {
        try {
            connection.login("zj", "123");
            System.out.println("login succssfull");
        }
        catch (XMPPException e) {
            e.printStackTrace();
        }
        MultiUserChat muc = new MultiUserChat(connection, "roomname"+conferenceServerJID);
        muc.join("zj","123456");
        muc.addMessageListener(new MyMUCListener());

        Message message = new Message();
        message.setBody("my message");
        message.setProperty("color", "red");
        muc.sendMessage(message);
        try {
            Thread.sleep(50000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 聊天室监听器
     */
    class MyMUCListener implements PacketListener {

        @Override
        public void processPacket(Packet packet) {
            Message message=(Message)packet;
            System.out.println("我接收到的消息： "+message.getBody());
        }
    }

    public static void main(String[] args) throws XMPPException {
        SmackXMPPTest smackXMPPTest = new SmackXMPPTest();
        smackXMPPTest.init();
//        smackXMPPTest.handleAccountManager();
//        smackXMPPTest.handleUserLogin();
//        smackXMPPTest.handleChatManager();
//        smackXMPPTest.createChatRoom();
//        smackXMPPTest.joinChatRoom();
        smackXMPPTest.communicateChatRoom();
        smackXMPPTest.destroy();
    }

}
