package com.ccthanking.common;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.ccthanking.business.sgenter.service.SgEnterPriseLibraryService;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.plugin.AppInit;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.spring.SpringContextHolder;

import jxl.*;
import jxl.write.*;

public class ExportAsExcel extends HttpServlet {
	public void doGet_temp(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {
		
		HttpSession session = request.getSession(true);
		String fileName = "表格打印";// (String) session.getAttribute("fileName");
		String querySql = "select * from XMXX"; // (String)
												// session.getAttribute("querySql");
		String info = request.getParameter("ExpTabListResultValue");
		String queryData = request.getParameter("ExpTabListQueryCondition");
		String tabThead = request.getParameter("ExpTabListThead");
		String fieldnames = request.getParameter("ExpTabListFieldNames");

		// session.removeAttribute("fileName");
		// session.removeAttribute("querySql");
		
		Connection conn = null;
		try {
			WritableFont arial15font = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);
			arial15font.setColour(jxl.format.Colour.LIGHT_BLUE);
			WritableCellFormat arial15format = new WritableCellFormat(arial15font);
			arial15format.setAlignment(jxl.format.Alignment.CENTRE);
			arial15format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			arial15format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);
			File file = new File("output.xls");
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("Sheet 1", 0);
			sheet.addCell(new Label(0, 0, fileName, arial15format));
			sheet.setName(fileName);
			conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(querySql);
			ResultSet rs = ps.executeQuery(querySql);
			if (rs != null) {
				WritableFont arial11font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD);
				WritableCellFormat arial11format = new WritableCellFormat(arial11font);
				arial11format.setAlignment(jxl.format.Alignment.CENTRE);
				arial11format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				arial11format.setBackground(jxl.format.Colour.RED);
				int row = 0;
				int col = 1;
				ResultSetMetaData rsmd = rs.getMetaData();
				int[] validColumn = new int[rsmd.getColumnCount()];
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i + 1);
					if (colName.indexOf("NextIsURL") != -1) {
						validColumn[i] = -1;
					} else if (colName.indexOf("ThisIsCheckBox") != -1) {
						validColumn[i] = -2;
					} else {
						sheet.addCell(new Label(row, col, colName, arial11format));
						validColumn[i] = getStrLen(colName) + 4;
						// sheet.setColumnView(row, validColumn[i]);
						row++;
					}
				}
				col++;
				if (row > 1) {
					sheet.mergeCells(0, 0, row - 1, 0);
				}
				WritableFont arial9font = new WritableFont(WritableFont.ARIAL, 9);
				WritableCellFormat arial9format = new WritableCellFormat(arial9font);
				// arial9format.setAlignment(Alignment.CENTRE);
				arial9format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
				WritableCellFormat dateCellFormat = new WritableCellFormat(arial9font, dateFormat);
				// dateCellFormat.setAlignment(Alignment.CENTRE);
				dateCellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				DateFormat timeFormat = new DateFormat("hh:mm:ss");
				WritableCellFormat timeCellFormat = new WritableCellFormat(arial9font, timeFormat);
				// timeCellFormat.setAlignment(Alignment.CENTRE);
				timeCellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				while (rs.next()) {
					row = 0;
					for (int i = 0; i < rsmd.getColumnCount(); i++) {
						if (validColumn[i] == -1 || validColumn[i] == -2) {
							continue;
						}
						int columnType = rsmd.getColumnType(i + 1);
						switch (columnType) {
						case Types.BIT:
						case Types.BIGINT:
						case Types.BOOLEAN:
						case Types.NUMERIC:
						case Types.REAL:
						case Types.SMALLINT:
						case Types.TINYINT:
						case Types.DECIMAL:
						case Types.FLOAT:
						case Types.INTEGER:
							float number = rs.getFloat(i + 1);
							sheet.addCell(new jxl.write.Number(row, col, number, arial9format));
							break;
						case Types.DATE:
						case Types.TIMESTAMP:
							Date date = rs.getDate(i + 1);
							if (date == null) {
								sheet.addCell(new jxl.write.Blank(row, col));
							} else {
								sheet.addCell(new jxl.write.DateTime(row, col, date, dateCellFormat));
							}
							break;
						case Types.TIME:
							Date time = rs.getDate(i + 1);
							if (time == null) {
								sheet.addCell(new jxl.write.Blank(row, col));
							} else {
								sheet.addCell(new jxl.write.DateTime(row, col, time, timeCellFormat));
							}
							break;
						default:
							String str = rs.getString(i + 1);
							if (str == null) {
								sheet.addCell(new jxl.write.Blank(row, col, arial9format));
							} else {
								str = str.trim();
								sheet.addCell(new Label(row, col, str, arial9format));
								int len = getStrLen(str);
								if (len > validColumn[i]) {
									validColumn[i] = len;
								}
							}
							break;
						}
						row++;
					}
					col++;
				}
				row = 0;
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					if (validColumn[i] > 0) {
						sheet.setColumnView(row, validColumn[i]);
						row++;
					}
				}
			}
			conn.close();
			workbook.write();
			workbook.close();
			response.setContentType("application/x-msdownload");
			fileName = new String(fileName.getBytes("gb2312"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls" + "\"");

			int len = (int) file.length();
			byte[] buf = new byte[len];
			FileInputStream fis = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("<html>");
			strBuffer.append("<head>");
			strBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=GBK\">");
			strBuffer.append("<meta name=ProgId content=Excel.Sheet>");
			strBuffer.append("<style>");
			strBuffer
					.append("table{ border:1px solid #000; border-spacing:0; border-collapse:collapse; padding:0; margin:0;}");
			strBuffer
					.append(" td,th{ border-right:1px solid #000; border-bottom:1px solid #000; padding:0; margin:0;}");
			strBuffer.append("</style>");
			strBuffer.append("</head>");
			strBuffer.append("<body >");
			// strBuffer.append(getTableList(querySql,tabThead,fieldnames));
			strBuffer.append("</body></html>");

			out.write(strBuffer.toString().getBytes("GB2312"));
			out.flush();
			out.close();
			// len = fis.read(buf);
			// out.write(buf, 0, len);
			// out.flush();
			// fis.close();
			// file.delete();
		} catch (Exception e) {
			System.out.println("[Info: ] User canceled - " + e.getMessage());
		}finally{
			DBUtil.closeConnetion(conn);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String encoding = request.getCharacterEncoding();
        encoding = StringUtils.isBlank(encoding)?"utf-8":encoding;
		request.setCharacterEncoding(encoding);
//		response.setContentType("text/html;charset=gbk");
		HttpSession session = request.getSession(true);
		String fileName = "表格打印";
		// String querySql ="select * from XMXX";
		// String info = request.getParameter("ExpTabListResultValue");
		//String resultJson = request.getParameter("ExpTabListResultValue");
		String queryData = request.getParameter("ExpTabListQueryCondition");
		String tabThead = request.getParameter("ExpTabListThead");
		String fieldnames = request.getParameter("ExpTabListFieldNames");
		String templateName = Pub.val(request, "templateName");
		String t_fieldnames = Pub.val(request, "fieldnames");
		String startXY = Pub.val(request, "startXY");
		String printFileName = Pub.val(request, "printFileName");
		String dao = request.getParameter("dao");
		String jsonStr = request.getParameter("jsonStr");
		//查询数据
    	String resultJson = ExcelUtil.queryDate(queryData, dao,jsonStr);

    	if (!Pub.empty(printFileName)) {
			fileName = printFileName;
		}

		try {
			if (Pub.empty(templateName)) {// 通用模式实现
				response.setContentType("application/x-msdownload");
				fileName = new String(fileName.getBytes("gb2312"), "ISO8859_1");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls" + "\"");
				OutputStream out = response.getOutputStream();
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append("<html>");
				strBuffer.append("<head>");
				strBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=GBK\">");
				strBuffer.append("<meta name=ProgId content=Excel.Sheet>");
				strBuffer.append("<style>");
				strBuffer.append("table{ border:1px solid #000; border-spacing:0; border-collapse:collapse; padding:0; margin:0;}");
				strBuffer.append(" td,th{ border-right:1px solid #000; border-bottom:1px solid #000; padding:0; margin:0;}");
				strBuffer.append("</style>");
				strBuffer.append("</head>");
				strBuffer.append("<body >");
				strBuffer.append(getTableListResultJson(resultJson,tabThead,fieldnames));
				strBuffer.append("</body></html>");

				out.write(strBuffer.toString().getBytes());

				out.flush();
				out.close();
			} else {// 指定模版模式
					// 导出文件的名称。当指定模板时，导出文件名称得设置一下编码，且必须放在这里。
				fileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
				HSSFWorkbook workBook = null;
				try {
					String templatepath = AppInit.appPath + "WEB-INF\\template\\" + templateName;// 此处为读取模版，需要自定义
					InputStream inp = new FileInputStream(templatepath);
					// 从源文件中进行读取
					if (inp != null) {
						workBook = new HSSFWorkbook(inp);
					}
					HSSFSheet sheet = workBook.getSheetAt(0);// 获得sheet页
					// 循环写结果集数据 begin

					JSONArray list = Pub.doInitJson(resultJson);
					int r = 1;// 起始行
					int c = 1;// 起始列
					if (!Pub.empty(startXY)) {
						if (startXY.indexOf(",") > -1) {
							String[] t = startXY.split(",");
							if (t != null && t.length > 0) {
								r = Integer.parseInt(t[0]);
								c = Integer.parseInt(t[1]);
							}
						}
					}
					String[] fieldname_ = t_fieldnames.split(",");
					//第一列样式
					HSSFCellStyle style_xh = workBook.createCellStyle();
					style_xh.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
					style_xh.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
					style_xh.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
					style_xh.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
					style_xh.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
					
					//其他列样式
					HSSFCellStyle style = workBook.createCellStyle();
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
					for (int i = 0; i < list.size(); i++) {
						JSONObject jsonObject = (JSONObject) list.get(i);
						HSSFRow row = sheet.createRow(r + i); // 创建一行
						if (fieldname_ != null && fieldname_.length > 0) {
							HSSFCell cell_xh = row.createCell((short) (c));
							cell_xh.setCellStyle(style_xh);
							cell_xh.setCellValue(i + 1);
							for (int j = 0; j < fieldname_.length; j++) {
								HSSFCell cell = row.createCell((short) (c + j + 1));
								cell.setCellStyle(style);
								// cell.setEncoding((short)HSSFCell.ENCODING_UTF_16);
								boolean t = jsonObject.containsKey(fieldname_[j].toUpperCase() + "_SV");
								if (t) {
									cell.setCellValue(jsonObject.getString(fieldname_[j].toUpperCase() + "_SV"));
								} else {
									cell.setCellValue(jsonObject.getString(fieldname_[j].toUpperCase()));
								}
							}

						}

					}
					response.setHeader("Content-type", "application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

					ServletOutputStream fileOut = response.getOutputStream();
					workBook.write(fileOut);
					fileOut.flush();
					fileOut.close();
				} catch (Exception e) {
					System.out.println("[Info: ] User canceled - " + e.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.println("[Info: ] User canceled - " + e.getMessage());
		}
	}

	public String getTableListBySql(String querySql, String tabThead, String fieldnames) throws SQLException {
		String tabHTML = "<table>";
		tabHTML += tabThead;
		String[] fn = fieldnames.split(",");
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(querySql);
			ResultSet rs = ps.executeQuery(querySql);
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int[] validColumn = new int[rsmd.getColumnCount()];
				while (rs.next()) {
					tabHTML += "<tr>";
					for (int j = 0; j < fn.length; j++) {

						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							String colName = rsmd.getColumnName(i + 1);
							if (fn[j].equalsIgnoreCase(colName)) {
								tabHTML += "<td>" + rs.getString(i + 1) + "</td>";
							}

						}
					}
					tabHTML += "</tr>";
				}

			}
			tabHTML += "</table>";
		} catch (Exception e) {
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return tabHTML;

	}
	public String getTableList(List<?> list ,String tabThead, String fieldnames) throws Exception{
		String tabHTML = "<table>";
		tabHTML += tabThead;
		String[] fn = fieldnames.split(",");
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map = (Map<String, Object>) list.get(i);
			tabHTML += "<tr><td>" + String.valueOf(i + 1) + "</td>";
			for (int j = 0; j < fn.length; j++) {
				for (String key : map.keySet()) {
					String value = (String) map.get(key);
					if (fn[j].equalsIgnoreCase(key)) {
						tabHTML += "<td>" + value + "</td>";
						break;
					}

		
				}
			}
			tabHTML += "</tr>";
		}
		tabHTML += "</table>";
		return tabHTML;
	}

	public String getTableListResultJson(String resultJson, String tabThead, String fieldnames) throws SQLException {
		String tabHTML = "<table>";
		tabHTML += tabThead;
		String[] fn = fieldnames.split(",");
		JSONArray list = doInitJson(resultJson);
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObject = (JSONObject) list.get(i);
			List<Object> arr = jsonObject.names();
			tabHTML += "<tr><td>" + String.valueOf(i + 1) + "</td>";
			for (int j = 0; j < fn.length; j++) {
				for (Object name : arr) {
					String key = name.toString().toUpperCase();
					String value = jsonObject.getString(name.toString());
					String fieldname = fn[j];
					String colspan = "";
					String colspanTd = "";
					if (fieldname.indexOf("|") > -1) {
						String[] s = Pub.getStringSplit(fieldname, "|");
						String kzJsonString = s[1];
						JSONObject kzJson = JSONObject.fromObject(kzJsonString);
						colspan = kzJson.getString("colspan");
						int t = 0;
						if (!Pub.empty(colspan)) {
							t = Integer.parseInt(colspan);
						}
						colspanTd += "colspan =\"" + t + "\"";
						fieldname = s[0];

					}

					if (fieldname.equalsIgnoreCase(key)) {
						String key_sv_value = "";
						;
						try {
							key_sv_value = jsonObject.getString(key + "_SV");
							if (key_sv_value != null && key_sv_value.indexOf("\b") > -1) {
								key_sv_value = key_sv_value.replaceAll("\b", " ");
							}
							tabHTML += "<td " + colspanTd + ">" + key_sv_value + "</td>";
						} catch (Exception e) {
							if (value != null && value.indexOf("\b") > -1) {
								value = value.replaceAll("\b", " ");
							}
							tabHTML += "<td " + colspanTd + ">" + value + "</td>";
						}
						break;
					}
				}
			}
			tabHTML += "</tr>";

		}
		tabHTML += "</table>";

		return tabHTML;

	}

	public JSONArray doInitJson(String initJson) {
		JSONObject response = JSONObject.fromObject(initJson);
		String response_txt = response.getString("response");
		JSONObject data = JSONObject.fromObject(response_txt);
		String data_txt = data.getString("data");
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(data_txt);
		return jsonArray;
	}

	public int getStrLen(String str) {
		if (str == null) {
			return 0;
		}
		byte[] buf = str.getBytes();
		return buf.length;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}