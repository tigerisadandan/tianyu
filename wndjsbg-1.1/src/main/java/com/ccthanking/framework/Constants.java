package com.ccthanking.framework;

public class Constants extends ConfigurableConstants {

	static {
		init("application.xml");
	}

	// ---------------从其它地方copy过来的放这个里面 ----------begin---

	// 是否启用dwr消息推送 true启用 false不启用
	public static String MSG_DWR_FLAG = "MSG_DWR_FLAG";
	// 是否创建jmemcache server true建 false不建
	public static String MEMCACHE_SERVER_CREATE_FLAG = "MEMCACHE_SERVER_CREATE_FLAG";

	// ---------------从其它地方copy过来的放这个里面 ----------end-----

	// 是否开发状态
	public static String APP_DEV_FLAG = "DEV_FLAG";

	// cookie name 登录名
	public static final String APP_COOKIE_NAME_REMBERUSERNAME = "bgremusname";

	// 上下文存放的 web文件上下文件路径 需要与--> web.xml 中保持一致
	public static String webAppRootKey = "webAppRootKey";

	// 应用程序名称 对应于菜单 fs_eap_menu.APP_NAME
	public static final String APP_NAME = "wndjsbg";

	// 加密方式
	public static String ENCODER_TYPE = "MD5";
	// 登录方式
	public static String LOGIN_TYPE = "springsecurity";

	// 客户端信息
	public static final String WEB_CLIENT = "WEB_CLIENT";

	// 来宾账号
	public static final String GUEST_ID = "guest";

	// 注意，该文件为保持兼容性而保留，新添加的常量定义须写在 Globals.java 中，避免直接
	// 使用 Constants 类。
	public static final String FILE_KEY = "GLOBAL.TMP_FILES";

	// 查看注册情况
	public static final String PAGE_SG_REG_RS_SHOW = "PAGE_SG_REG_RS_SHOW";

	// 施工企业入库注册
	public static final String PAGE_SG_ENTER_REG = "PAGE_SG_ENTER_REG";

	// 施工企业入库注册
	public static final String PAGE_SG_ENTER_EDIT = "PAGE_SG_ENTER_EDIT";

	// 施工企业审批信息
	public static final String PAGE_SG_ENTER_ONSP = "PAGE_SG_ENTER_ONSP";

	// 施工企业入库注册声明
	public static final String PAGE_SG_ENTER_REG_BEFORE = "PAGE_SG_ENTER_REG_BEFORE";

	// 施工人员信息新增
	public static final String PAGE_SG_PERSON_ADD = "PAGE_SG_PERSON_ADD";
	public static final String PAGE_SG_PERSON_ADD_ONE = "PAGE_SG_PERSON_ADD_ONE";
	// 施工人员详细信息
	public static final String PAGE_SG_PERSON_DETAIL = "PAGE_SG_PERSON_DETAIL";

	// 入库人员可以选择锁定
	public static final String PAGE_SG_PERSON_SUODING = "PAGE_SG_PERSON_SUODING";
	
	// 监理入库人员可以选择锁定
	public static final String PAGE_JL_PERSON_SUODING = "PAGE_JL_PERSON_SUODING";
	
	// 施工报备新增
	public static final String PAGE_SGBB_ADD = "PAGE_SGBB_ADD";

	// 施工企业审核信息
	public static final String PAGE_SGENTERPRISE_SHENPIINFO = "PAGE_SGENTERPRISE_SHENPIINFO";

	// 施工企业审批历史
	public static final String PAGE_SGENTERPRISE_SHENPI = "PAGE_SGENTERPRISE_SHENPI";

	// 施工企业信息修改
	public static final String PAGE_SGENTERPRISE_XIUGAI = "PAGE_SGENTERPRISE_XIUGAI";

	// 施工报备新增
	public static final String PAGE_SGBBXX_ADD = "PAGE_SGBBXX_ADD";

	// 施工报备新增
	public static final String PAGE_SGBBXX_EDIT = "PAGE_SGBBXX_EDIT";

	// 施工报备新增
	public static final String PAGE_SGBBXX_VIEW = "PAGE_SGBBXX_VIEW";
	
	
	public static final String PAGE_JL_ENTER_SH = "PAGE_JL_ENTER_SH";

	// 附件显示
	public static final String DATA_FUJIAN_SHOW = "DATA_FUJIAN_SHOW";

	// 附件上传地址别名
	public static final String FILEUPLOADOLD_ROOT = "FILEUPLOADOLD_ROOT";
	public static final String FILEUPLOADOLD_ROOT_JS = "FILEUPLOADOLD_ROOT_WNDJSJS";
	
	public static final String FILEUPLOADOLD_ROOT_JL = "FILEUPLOADROOT_WNDJSJL";
	
	public static final String FILEUPLOADOLD_ROOT_GD = "FILEUPLOADROOT_WNDJSGD";


	// 业务信息添加
	public static final String PAGE_SPYW_YWXX_ADD = "PAGE_SPYW_YWXX_ADD";

	// 业务信息查看/修改
	public static final String PAGE_SPYW_YWXX_VIEW = "PAGE_SPYW_YWXX_VIEW";

	// 业务信息树
	public static final String PAGE_SPYW_YWXX_LAYER = "PAGE_SPYW_YWXX_LAYER";
	
	
	public static final String PAGE_Jl_PERSON_ADD = "PAGE_Jl_PERSON_ADD";
	
	public static final String PAGE_Jl_PERSON_EDIT = "PAGE_Jl_PERSON_EDIT";
	
	
	public static final String PAGE_JL_ENTER_EDIT_RK = "PAGE_JL_ENTER_EDIT_RK";
	
	
	

	// QY－企业材料；LX－立项材料；XM－项目材料；YW-业务材料；CL-具体的材料文件节点
	public static final String YWCLKJB_QY = "QY";
	public static final String YWCLKJB_LX = "LX";
	public static final String YWCLKJB_XM = "XM";
	public static final String YWCLKJB_YW = "YW";
	public static final String YWCLKJB_CL = "CL";

	public static final String FS_FILEUPLOAD_FJLB_QYCL = "1601";// 企业申请，修改上传的材料
	public static final String FS_FILEUPLOAD_FJLB_LXCL = "1602";// 立项时或后期修改上传的材料
	public static final String FS_FILEUPLOAD_FJLB_XMCL = "1603";// 项目申请或后期修改上传的材料
	public static final String FS_FILEUPLOAD_FJLB_QYCLKCL = "1604";// 企业材料库集中上传的材料
	public static final String FS_FILEUPLOAD_FJLB_HFCL = "1605";// 核发材料
	public static final String FS_FILEUPLOAD_FJLB_YWCL = "1606";// 业务实例上传的材料
	public static final String FS_FILEUPLOAD_FJLB_SPYWCL = "1607";// 审批业务材料上传的材料

	public static final String FS_FILEUPLOAD_FJLB_QYYYZZCL = "1608";// 企业申请，上传的营业执照
	public static final String FS_FILEUPLOAD_FJLB_QYZZJGDMCL = "1609";// 企业申请，上传的组织机构代码执照
	// 排水施工图
	public static final String PAGE_SPYW_PSSGT_ADD = "PAGE_SPYW_PSSGT_ADD";
	public static final String PAGE_SPYW_PSSGT_VIEW = "PAGE_SPYW_PSSGT_VIEW";
	
	//新型墙体材料专项基金返退申请
	public static final String PAGE_SPYW_XXQTCLZXJJFTSQ_ADD = "PAGE_SPYW_XXQTCLZXJJFTSQ_ADD";
	public static final String PAGE_SPYW_XXQTCLZXJJFTSQ_VIEW = "PAGE_SPYW_XXQTCLZXJJFTSQ_VIEW";
	
	// 排水方案预审
	public static final String PAGE_SPYW_PSFAYS_ADD = "PAGE_SPYW_PSFAYS_ADD";
	public static final String PAGE_SPYW_PSFAYS_VIEW = "PAGE_SPYW_PSFAYS_VIEW";

	// 建设工程施工许可
	public static final String PAGE_SPYW_JSGCSGXK_ADD = "PAGE_SPYW_JSGCSGXK_ADD";
	public static final String PAGE_SPYW_JSGCSGXK_VIEW = "PAGE_SPYW_JSGCSGXK_VIEW";

	// 商品房交付使用验收申请
	public static final String PAGE_SPYW_SPFJFSYYSSQ_ADD = "PAGE_SPYW_SPFJFSYYSSQ_ADD";
	public static final String PAGE_SPYW_SPFJFSYYSSQ_VIEW = "PAGE_SPYW_SPFJFSYYSSQ_VIEW";

	// 商品房交付使用竣工验收项目情况
	public static final String PAGE_SPYW_SPFJFSYJGYSXMQK_ADD = "PAGE_SPYW_SPFJFSYJGYSXMQK_ADD";
	public static final String PAGE_SPYW_SPFJFSYJGYSXMQK_VIEW = "PAGE_SPYW_SPFJFSYJGYSXMQK_VIEW";

	// 雨污水入网申请
	public static final String PAGE_SPYW_YWSRWSQ_ADD = "PAGE_SPYW_YWSRWSQ_ADD";
	public static final String PAGE_SPYW_YWSRWSQ_VIEW = "PAGE_SPYW_YWSRWSQ_VIEW";

	// 施工企业业绩核查事项
	public static final String PAGE_SPYW_SGQYYJHCSX_ADD = "PAGE_SPYW_SGQYYJHCSX_ADD";
	public static final String PAGE_SPYW_SGQYYJHCSX_VIEW = "PAGE_SPYW_SGQYYJHCSX_VIEW";
	
	// 燃气供应许可证
	public static final String PAGE_SPYW_RQGYXKZ_SQ_ADD = "PAGE_SPYW_RQGYXKZ_SQ_ADD";
	public static final String PAGE_SPYW_RQGYXKZ_SQ_VIEW = "PAGE_SPYW_RQGYXKZ_SQ_VIEW";
	
	// 建设项目环境影响登记表告知承诺备案-工业他类
	public static final String PAGE_SPYW_GZCNBA_GY_ADD = "PAGE_SPYW_GZCNBA_GY_ADD";
	public static final String PAGE_SPYW_GZCNBA_GY_VIEW = "PAGE_SPYW_GZCNBA_GY_VIEW";

	// 建设项目环境影响登记表告知承诺备案-区域开发及其他类
	public static final String PAGE_SPYW_GZCNBA_QYKF_ADD = "PAGE_SPYW_GZCNBA_QYKF_ADD";
	public static final String PAGE_SPYW_GZCNBA_QYKF_VIEW = "PAGE_SPYW_GZCNBA_QYKF_VIEW";

	// 建设项目环境影响登记表告知承诺备案-饮食娱乐服务类
	public static final String PAGE_SPYW_GZCNBA_YSYL_ADD = "PAGE_SPYW_GZCNBA_YSYL_ADD";
	public static final String PAGE_SPYW_GZCNBA_YSYL_VIEW = "PAGE_SPYW_GZCNBA_YSYL_VIEW";

	// 建设项目环境影响评价报告表（书）审批-区域开发及其他类
	public static final String PAGE_SPYW_HJYXPJ_GY_ADD = "PAGE_SPYW_HJYXPJ_GY_ADD";
	public static final String PAGE_SPYW_HJYXPJ_GY_VIEW = "PAGE_SPYW_HJYXPJ_GY_VIEW";

	// 建设项目环境影响评价报告表（书）审批-区域开发及其他类
	public static final String PAGE_SPYW_HJYXPJ_QYKF_ADD = "PAGE_SPYW_HJYXPJ_QYKF_ADD";
	public static final String PAGE_SPYW_HJYXPJ_QYKF_VIEW = "PAGE_SPYW_HJYXPJ_QYKF_VIEW";

	// 建设项目环境影响评价报告表（书）审批-饮食娱乐服务类
	public static final String PAGE_SPYW_HJYXPJ_YSYL_ADD = "PAGE_SPYW_HJYXPJ_YSYL_ADD";
	public static final String PAGE_SPYW_HJYXPJ_YSYL_VIEW = "PAGE_SPYW_HJYXPJ_YSYL_VIEW";

	//无锡新区房屋建筑和市政基础设施工程施工、监理直接发包及合同备案
	public static final String PAGE_SPYW_CBFASP_ADD = "PAGE_SPYW_CBFASP_ADD";
	public static final String PAGE_SPYW_CBFASP_VIEW = "PAGE_SPYW_CBFASP_VIEW";
	
	//无锡新区监理工程直接发包初步方案审批表
	public static final String PAGE_SPYW_CBFASP2_ADD = "PAGE_SPYW_CBFASP2_ADD";
	public static final String PAGE_SPYW_CBFASP2_VIEW = "PAGE_SPYW_CBFASP2_VIEW";
	
	// 模板文件
	public static final String PATH_TEMPLATE_WORD = "PATH_TEMPLATE_WORD";
	// 转换时生成的xml、pdf
	public static final String PATH_TEMPLATE_XML = "PATH_TEMPLATE_XML";
	//步骤文件 
	public static final String PATH_TEMPLATE_BZWJ_WORD = "PATH_TEMPLATE_BZWJ_WORD";

	// 业务流转信息列表
	public static final String PAGE_SPYW_YWLZ_INDEX = "PAGE_SPYW_YWLZ_INDEX";

	// 业务流转页面读取类别
	public static final String YWLZ_DO_EDIT = "doEdit";
	public static final String YWLZ_DO_INSERT = "doAdd";
	
	//安装 过程图片
			public static final String FS_FILEUPLOAD_FJLB_JXSB_AZGC = "6000";
		
		//拆卸过程图片
		public static final String FS_FILEUPLOAD_FJLB_JXSB_CXGC = "6001";
	
	// fs_fileupload 附件类别
		public static final String FS_FILEUPLOAD_FJLB_QY_YYZZ = "11";// 企业     营业执照   附件类别
		public static final String FS_FILEUPLOAD_FJLB_QY_ZZJGDM = "13";//企业    组织机构代码     附件类别
		public static final String FS_FILEUPLOAD_FJLB_QY_SWZM = "12";// 企业   税务证明附件类别税务证明   附件类别
		public static final String FS_FILEUPLOAD_FJLB_LX_LXPW = "41";// 立项         立项批文     附件类别
		public static final String FS_FILEUPLOAD_FJLB_LX_KYPF = "17";// 立项        可研批复    附件类别
		public static final String FS_FILEUPLOAD_FJLB_LX_XMGS = "9";//立项         项目概算   附件类别
		public static final String FS_FILEIPLOAD_FJLB_QY_SQWTS = "409"; // 授权委托书
		public static final String FS_FILEIPLOAD_FJLB_QY_CNS = "410"; // 承诺书
		public static final String FS_FILEIPLOAD_FJLB_JBRSFZ = "411"; // 经办人身份证
		public static final String FS_FILEIPLOAD_FJLB_JBRZP = "412"; // 经办人照片
		
		
		/***预选承包商类型******/
		public static final String YXCBS_LX_SG="SG";//施工
		public static final String YXCBS_LX_JL="JL";//监理
		public static final String YXCBS_LX_LH="LH";//绿化曹苜
		public static final String YXCBS_LX_KC="KC";//勘察
		public static final String YXCBS_LX_CLSB="SB";//设备材料
		
		//建设手续
	    public static final String JS_SPYW_ZJFB ="124";//业务 -房屋市政基础施工 监理备案-直接发包手续
	    public static final String JS_SPYW_GKFB ="123";//业务 -房屋市政基础施工 监理备案-公开法宝手续
	    public static final String JS_SPYW_SQXKZ ="33";//申请许可证手续
	    public static final String JS_SPYW03_HFCL_SG_CLUID="364"; //建设手续 (房屋建筑和市政基础设施工程施工、监理发包合同备案) 建设项目直接发包合同备案表_施工 核发文件 材料库uid
	    public static final String JS_SPYW03_HFCL_JL_CLUID="365"; //建设手续 (房屋建筑和市政基础设施工程施工、监理发包合同备案) 建设项目直接发包合同备案表_监理 核发文件 材料库uid
		
	//施工手续
		//施工企业资格审查及安全监督、文明施工申报 ywid 
	    public static final String SG_SX_JZGCSB_YWID="114";
	    // 市政工程企业资格审查及安全监督、文明施工申报 ywid 
	    public static final String SG_SX_SZGCSB_YWID="115";
	    //开工前安全条件复查 
	    public static final String SG_SX_KGQAQTJFC_YWID="117";
	    // 人脸识别考勤备案
	    public static final String SG_SX_RLSBXT_YWID="118";
	    //建筑工程施工注册证及安监备案 
	    public static final String SG_SX_JZGCSGZCZBA_YWID="119";

	  //危险源 过程管理 
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_GDMBGC_ZJYS = "3001"; //高支模版工程 过程管理 中间验收
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_DZGCGC = "3002"; //高支模版工程 过程管理 中间验收
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_MQGC="3003";//幕墙工程
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_GJGGC="3004";//钢结构工程
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_WJGC="3005";//网架工程
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_SJK_TFKW="3006";//深基坑工程 土方开挖
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_SJK_PM="3007";//深基坑   喷锚
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_SJK_TFHT="3008";//网架工程 土方回填
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_JSJ_ZJYS="3009";//网架工程 中间验收
	  		public static final String FS_FILEUPLOAD_FJLB_WXY_JSJ_JCYS="3010";//网架工程 检测验收
	    
	//较大危险源  超规模参数
	    public static final String GCQD_TYPE1="基坑支护、降水及土方开挖工程"; 
	    public static final int GCQD_KAIWA_DEEP=5;
	    public static final String GCQD_TFKWGC_Y="有";
	    public static final String GCQD_TYPE2="模板工程及支撑体系"; 
	    public static final String GCQD_GLGJ_Y="有";
	    public static final int GCQD_TSGD=8;
	    public static final int GCQD_TSKD=18;
	    public static final int GCQD_SGZZH=15;
	    public static final int GCQD_JZXZH=20;
	    public static final String GCQD_ZHICHENGGC_Y="有";
	    public static final int GCQD_CSDDJZZH=700;
	    public static final String GCQD_TYPE3="起重吊装安装拆卸工程"; 
	    public static final int GCQD_DJQDZL=100;
	    public static final int GCQD_QZL=300;
	    public static final int GCQD_AZGD=200;
	    public static final String GCQD_TYPE4="脚手架工程";
	    public static final int GCQD_TS_GD=50;
	    public static final int GCQD_TISHENG_HEIGHT=150;
	    public static final int GCQD_JIATI_HEIGHT=20;
	    public static final String GCQD_DLJSJGC_Y="有";
	    public static final String GCQD_ZZYCPT_Y="有";
	    public static final String GCQD_JSJGC_Y="有";
	    public static final String GCQD_TYPE5="拆除、爆破工程";
	    public static final String GCQD_CCGC_Y="有";
	    public static final String GCQD_BPCCGC_Y="有";
	    public static final String GCQD_GJWCCGC_Y="有";
	    public static final String GCQD_YXCCGC_Y="有";
	    public static final String GCQD_BFJZCCGC_Y="有";
	    public static final String GCQD_TYPE6="幕墙工程";
	    public static final int GCQD_QT_SG_HEIGHT=50;
	    public static final String GCQD_TYPE7="钢结构工程";
	    public static final String GCQD_TYPE7_GJG="钢结构工程GJG";
	    public static final String GCQD_TYPE7_MJ="钢结构工程WJ";
	    
	    public static final int GCQD_QT_KD_GJG=36;
	    public static final int GCQD_QT_KD_SMJG=60;
	    
	    /***消息动态 MSG_TYPE ******/
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JSSX = "建设手续";
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JSXM = "建设项目";
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JSQY_XXBG = "建设企业信息变更";
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JSXMFQ = "建设项目分期";
		
		public static final String FS_MESSAGE_INFO_MSG_TYPE_SGQYRY = "施工企业人员";
		public static final String FS_MESSAGE_INFO_MSG_TYPE_SGQYXX = "施工企业信息";
		
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JLQYRY = "监理企业人员";
		public static final String FS_MESSAGE_INFO_MSG_TYPE_JLQYXX = "监理企业信息";
		
	    /**
	     * 消息动态 FS_MESSAGE_INFO QUANXIAN表权限点UID 
	     */
		/**企业信息维护 */
		public static final String QUANXIAN_JS_QYXXWH = "102";
		/**项目信息维护 */
		public static final String QUANXIAN_JS_XMXXWH = "103";
		/**建设手续申报 */
		public static final String QUANXIAN_JS_JSSXSB = "105";
		/**环保手续申报  */
		public static final String QUANXIAN_JS_HBSXSB = "106";
		
		
		/** -----------------施工--------------------  */
		/**企业管理  */
		public static final String QUANXIAN_SG_QYGL = "51";
		/**人员管理  */
		public static final String QUANXIAN_SG_RYGL = "53";
		/**业务管理  */
		public static final String QUANXIAN_SG_YWGL = "55";
		/**微型工程  */
		public static final String QUANXIAN_SG_WXGC = "57";
		/**项目动态管理  */
		public static final String QUANXIAN_SG_XMDTGL = "58";
		/**用户管理  */
		public static final String QUANXIAN_SG_YHGL = "53";
		
		
		/** -----------------监理--------------------  */
		
		/**人员管理*/
		public static final String QUANXIAN_JL_RYGL = "13";
		/**企业管理*/
		public static final String QUANXIAN_JL_QYGL = "11";
		
		
}
