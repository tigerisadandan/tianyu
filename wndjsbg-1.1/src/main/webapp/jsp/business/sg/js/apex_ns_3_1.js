var NameSpaceOnly=false;var apex={};if(apex===null||typeof (apex)!="object"){apex={}}if(apex.page===null||typeof (apex.page)!="object"){apex.page={}}if(apex.tabular===null||typeof (apex.tabular)!="object"){apex.tabular={}}if(apex.spreadsheet===null||typeof (apex.spreadsheet)!="object"){apex.spreadsheet={}}if(apex.items===null||typeof (apex.items)!="object"){apex.items={}}if(apex.util===null||typeof (apex.util)!="object"){apex.util={}}if(apex.ajax===null||typeof (apex.ajax)!="object"){apex.ajax={}}if(apex.dhtml===null||typeof (apex.dhtml)!="object"){apex.dhtml={}}if(apex.worksheet===null||typeof (apex.worksheet)!="object"){apex.worksheet={}}if(apex.validation===null||typeof (apex.validation)!="object"){apex.validation={}}if(apex.widget===null||typeof (apex.widget)!="object"){apex.widget={}}apex={util:function(){var A=this;this.el=false;this.init=function(B){A.el=$x(B);if(!A.el){return false}};this.select={};this.select.clean=function(B){A.init(B);if(A.el.length==1&&A.el.options[0].value=="DELETE ME"){A.el.length--}};return A}};var gUtil=new apex.util();apex.page={form:function(B){var A=this;A.request=B;A.supress=false;A.submit=function(C){if(C){A.request=C}if(!A.supress){flowSelectAll();$s("pRequest",A.request);document.wwv_flow.submit()}};this.confirm=function(D,C){if(!!!C){C="Delete"}A.supress=!confirm((!!!D)?"Would you like to perform this delete action?":D);A.submit(C)}},item:function(A){var C=this;this.node=false;this.item_type=false;this.id=false;this.value=D;this.valueArray=B;this.init=E;this.set=F;this.init(A);return ;function E(G){try{switch(typeof (G)){case"string":C.node=document.getElementById(G);break;case"object":C.node=G;break;default:C.node=false;break}if(C.node.nodeType==1){}else{C.node=false}}catch(J){C.node=false}if(C.node){C.id=C.node.id;var H=C.node.nodeName.toUpperCase();var I=C.node.className.toUpperCase();if(H=="FIELDSET"){C.item_type=I;switch(I){case"CHECKBOX_GROUP":break;case"RADIO_GROUP":break;case"SHUTTLE":break;case"LISTMANAGER":break;default:C.item_type=false;break}}else{if(H=="INPUT"){C.item_type=C.node.type.toUpperCase();switch(C.item_type){case"CHECKBOX":break;case"RADIO":break;case"TEXT":break;case"PASSWORD":break;case"HIDDEN":break;case"FILE":break;default:C.item_type=false;break}}else{C.item_type=H;switch(C.item_type){case"TEXTAREA":break;case"SELECT":break;case"SPAN":C.item_type=false;break;default:C.item_type=false;break}}}}}function B(){if(!C.node){return false}var K=true,L=[];switch(C.item_type){case"RADIO_GROUP":H=$x_FormItems(C.node,"RADIO");for(var I=0,G=H.length;I<G;I++){if(H[I].checked){L[L.length]=H[I].value}}break;case"CHECKBOX_GROUP":H=$x_FormItems(C.node,"CHECKBOX");for(var I=0,G=H.length;I<G;I++){if(H[I].checked){L[L.length]=H[I].value}}break;case"SHUTTLE":H=$x(C.node.id+"_RIGHT").options;for(var I=0,G=H.length;I<G;I++){L[L.length]=H[I].value}break;case"LISTMANAGER":H=$x(C.node.id+"_LISTMGRDATA").options;for(var I=0,G=H.length;I<G;I++){L[L.length]=H[I].value}break;case"SELECT":var H=C.node.options;for(var I=0,G=H.length;I<G;I++){if(H[I].selected){L[L.length]=H[I].value}}break;default:L=[false];K=false;break}if(K){}else{switch(C.item_type){case"CHECKBOX":L=[(C.node.checked)?C.node.value:false];break;case"RADIO":L=[(C.node.checked)?C.node.value:false];break;case"TEXT":L=[C.node.value];break;case"PASSWORD":L=[C.node.value];break;case"HIDDEN":L=[C.node.value];break;case"FILE":L=[C.node.value];break;case"TEXTAREA":if(C.node.parentNode.className="html_editor"&&C.node.parentNode.tagName=="FIELDSET"){var J=FCKeditorAPI.GetInstance(C.node.id);L=[J.GetHTML()]}else{L=[C.node.value]}break;default:L=[false];break}}return L}function D(){lReturn=C.valueArray();lReturn=$u_ArrayToString(lReturn,":");return lReturn}function F(J){var I=false;if(!C.node){return }switch(C.item_type){case"RADIO_GROUP":I=$x_FormItems(C.node,"RADIO");break;case"CHECKBOX_GROUP":I=$x_FormItems(C.node,"CHECKBOX");break;case"LISTMANAGER":I=$x(C.node.id+"_LISTMGRDATA").options;break;case"SELECT":var I=C.node.options;break;default:I=false;break}if(I){for(var K=0,H=I.length;K<H;K++){var G=(I[K].value==J)?true:false;if(C.item_type=="RADIO_GROUP"||C.item_type=="CHECKBOX_GROUP"){I[K].checked=G}else{I[K].selected=G}}}else{switch(C.item_type){case"CHECKBOX":(C.node.value==J)?C.node.checked=true:null;case"RADIO":(C.node.value==J)?C.node.checked=true:null;case"TEXT":C.node.value=J;break;case"PASSWORD":C.node.value=J;break;case"HIDDEN":C.node.value=J;break;case"FILE":C.node.value=J;break;case"TEXTAREA":if(C.node.parentNode.className="html_editor"&&C.node.parentNode.tagName=="FEILDSET"){var L=FCKeditorAPI.GetInstance(C.node.id);lReturn=[L.GetHTML()]}else{C.node.value=J}break;default:C.node.innerHTML=J;break}if(C.node.getAttribute("onchange")||C.node.onchange){C.node.onchange()}}}return }};apex.validation={v:function(){var A=this;this.get_emptys=function(E,I,D){var G=[];var C=[];if($x(E)){E=[E]}for(var F=0,B=E.length;F<B;F++){var H=$x(E[F]);if(H){if(isEmpty(H)){G[G.length]=H}else{C[C.length]=H}}}if(I){$x_Class(G,I)}if(D){$x_Class(C,D)}if(G.length==0){G=false}else{G[0].focus()}return G};this.is_in=function(E,D){var G=[];var C=[];if($x(E)){E=[E]}for(var F=0,B=E.length;F<B;F++){var I=$x(E[F]);for(var H=0,B=D.length;H<B;H++){if(I){if(I.value==D[H]){G[G.length]=I}}}}if(G.length==0){G=false}else{G[0].focus()}return G};this.basic_sql=function(D,E,G,C,B){var F=[D,E,G];if(!!($f_is_in(E,["is null","is not null"]))){F=[D,E]}return $f_get_emptys(F,C,B)}}};function $d_LOV_from_JSON(){var B=this;this.l_Type=false;this.l_Json=false;this.l_This=false;this.l_JSON=false;this.l_Id="json_temp";this.l_NewEls=[];this.create=A;this.l_Dom=false;return ;function A(I,G,K,O){var D=$u_eval("("+G+")");if(B.l_Type=="SHUTTLE"){var T='<table cellspacing="0" cellpadding="0" border="0" class="ajax_shuttle" summary=""><tbody><tr><td class="shuttleSelect1" id="shuttle1"></td><td align="center" class="shuttleControl"><img title="Reset" alt="Reset" onclick="g_Shuttlep_v01.reset();" src="/i/htmldb/icons/shuttle_reload.png"/><img title="Move All" alt="Move All" onclick="g_Shuttlep_v01.move_all();" src="/i/htmldb/icons/shuttle_last.png"/><img title="Move" alt="Move" onclick="g_Shuttlep_v01.move();" src="/i/htmldb/icons/shuttle_right.png"/><img title="Remove" alt="Remove" onclick="g_Shuttlep_v01.remove();" src="/i/htmldb/icons/shuttle_left.png"/><img title="Remove All" alt="Remove All" onclick="g_Shuttlep_v01.remove_all();" src="/i/htmldb/icons/shuttle_first.png"/></td><td class="shuttleSelect2" id="shuttle2"></td><td class="shuttleSort2"><img title="Top" alt="Top" onclick="g_Shuttlep_v01.sort2(\'T\');" src="/i/htmldb/icons/shuttle_top.png"/><img title="Up" alt="Up" onclick="g_Shuttlep_v01.sort2(\'U\');" src="/i/htmldb/icons/shuttle_up.png"/><img title="Down" alt="Down" onclick="g_Shuttlep_v01.sort2(\'D\');" src="/i/htmldb/icons/shuttle_down.png"/><img title="Bottom" alt="Bottom" onclick="g_Shuttlep_v01.sort2(\'B\');" src="/i/htmldb/icons/shuttle_bottom.png"/></td></tr></tbody></table>';$x(I).innerHTML=T;var M=$dom_AddTag("shuttle1","select");var R=$dom_AddTag("shuttle2","select");M.multiple=true;R.multiple=true;for(var Q=0,S=D.row.length;Q<S;Q++){if(!!D.row[Q]){var P=(!!D.row[Q].C)?parseInt(D.row[Q].C):false;if(P){var J=$dom_AddTag(R,"option")}else{var J=$dom_AddTag(M,"option")}J.text=D.row[Q].D;J.value=D.row[Q].R}}window.g_Shuttlep_v01=null;if(!C){var C=[]}C[2]=M;C[1]=R;window.g_Shuttlep_v01=new dhtml_ShuttleObject(M,R);return window.g_Shuttlep_v01}else{if(B.l_Type=="SELECT"||B.l_Type=="MULTISELECT"){var M=$dom_AddTag(I,"select");for(var Q=0,S=D.row.length;Q<S;Q++){if(!!D.row[Q]){var J=$dom_AddTag(M,"option");J.text=D.row[Q].D;J.value=D.row[Q].R;var P=parseInt(D.row[Q].C);J.selected=P}}B.l_Dom=M;return B}else{if(B.l_Type=="CHECKBOX"){var E=$dom_AddTag(I,"table");for(var Q=0,S=D.row.length;Q<S;Q++){if(!!D.row[Q]){if(Q%10==0){lrow=$dom_AddTag(E,"tr")}var F=$dom_AddTag(lrow,"td");var P=parseInt(D.row[Q].C);var H=$dom_AddInput(F,"checkbox",D.row[Q].R);H.checked=P;$dom_AddTag(F,"span",D.row[Q].D)}}B.l_Dom=M;return B}else{var L=$dom_AddTag(I,"div");for(var Q=0,S=D.row.length;Q<S;Q++){if(!!D.row[Q]){var N=(!!D.row[Q].D)?D.row[Q].D:D.row[Q].R;var U=$dom_AddTag(L,B.l_Type.toUpperCase(),N);B.l_NewEls[B.l_NewEls.length]=U;U.id=D.row[Q].R;var P=parseInt(D.row[Q].C);if(P){U.className="checked"}}}B.l_Dom=L;return B}}}}}apex.worksheet={ws:function(A){var B=this;this.ajax_busy=false;this.worksheet_id=false;this.report_id=false;this.current_col_id=false;this.last_col_id=false;this.current_control=false;this.active_dialog=false;this.supress_update=false;this.external_items=false;this.init=C;this.init(A);this.toggle_controls=function(){var D=($x("apexir_CONTROL_PANEL_COMPLETE").style.display!="none")?"Y":"N";$x_ToggleWithImage("apexir_CONTROLS_IMAGE",["apexir_CONTROL_PANEL_COMPLETE","apexir_CONTROL_PANEL_SUMMARY"]);B.supress_update=true;B.action("CONTROL_MIN",false,false,D)};this.item={};this.item.worksheet_holder=function(){return $x("apexir_WORKSHEET_REGION")};this.item.worksheet_detail=function(){return $x("apexir_DETAIL")};this.item.worksheet_report=function(){return $x("apexir_REPORT")};this.item.worksheet_div=function(){return $x("apexir_WORKSHEET")};this.item.control_panel_drop=function(){return $x("apexir_CONTROL_PANEL_DROP")};this.item.ws_control_panel=function(){return $x("apexir_CONTROL_PANEL")};this.item.worksheet_id=function(){return $x("apexir_NUM_ROWS")};this.item.search=function(){return $x("apexir_SEARCH")};this.item.search_column=function(){return $x("apexir_CURRENT_SEARCH_COLUMN")};this.dialog={};this.dialog.check=function(F){var E=html_GetTarget(F);var D=$x("apexir_rollover");var G=true;while(E.nodeName!="BODY"){E=E.parentNode;if(E==D){G=false}}if(G){B.dialog.reset()}};this.dialog.check2=function(F){var E=html_GetTarget(F);var D=$x("apexir_col_values_drop");var G=true;while(E.nodeName!="BODY"){E=E.parentNode;if(E==D){G=false}}if(G){$x_Remove("apexir_col_values_drop")}};this.dialog.reset=function(){if(B.supress_update){}else{$d_ClearAndHide(["searchdrop",B.item.control_panel_drop(),"apexir_SEARCHDROP"]);$x_Hide(["searchdrop","apexir_rollover"]);$s("apexir_rollover_content","");if($x(B.last_col_id)){$x_Class($x(B.last_col_id).parentNode,"")}B.dialog.id=false;document.body.onclick=""}};this.dialog.util_exp_type=function(){var D={};D.col=$x("apexir_COLUMN_NAME");D.col_type=D.col.options[D.col.selectedIndex].className;D.col_opt=$x("apexir_"+D.col_type+"_OPT");D.col_opt_val=$v(D.col_opt);if(D.col_type=="DATE"&&!(D.col_opt_val=="is in the last"||D.col_opt_val=="is not in the last"||D.col_opt_val=="is in the next"||D.col_opt_val=="is not in the next")){D.form_items=["apexir_BETWEEN_FROM","apexir_BETWEEN_TO"]}else{D.form_items=["apexir_EXPR","apexir_EXPR2"]}return D};this.dialog.validate=function(F){var E=[];var D=B.dialog.util_exp_type();switch(true){case (D.col_opt_val=="between"):E=[D.form_items[0],D.form_items[1]];break;case (D.col_opt_val=="is null"||D.col_opt_val=="is not null"):E=[];break;case (D.col_opt_val=="is in the last"||D.col_opt_val=="is not in the last"||D.col_opt_val=="is in the next"||D.col_opt_val=="is not in the next"):E=[D.form_items[0],"apexir_EXPR3"];break;default:E=[D.form_items[0]]}if($f_get_emptys(E,"error","")){return false}else{return D}};this.controls={};this.controls.cancel=function(){B.dialog.reset()};this.controls.save_toggle=function(E){var D=($v(E)=="NAMED")?"apexir_SAVE_NAMED":"apexir_SAVE_DEFAULT";$x_HideSiblings(D)};this.controls.drop=function(){return B.item.control_panel_drop()};this.controls.get=function(E,D){B.dialog.reset();B.l_Action="CONTROL";B.l_Type=E;B.current_control=E;B.current_dom=$x(D);if(D){B.current_col_id=D}B._Get("CONTROL",E,false,D)};this.controls.menu=function(E,D){B.dialog.reset();app_AppMenuMultiOpenBottom2(E,D,false)};this.controls.init=function(D){};this.controls.widget=function(D){this.get("SORT_WIDGET",D);B.current_col_dom=$x(D);document.body.onclick=B.dialog.check};this.controls.narrow=function(D){B.supress_update=true;B.temp_return_element=$x(D);this.get("NARROW",$v("apexir_COLUMN_NAME"))};this.controls.format_mask=function(D){B.supress_update=true;B.temp_return_element=$x(D);this.get("FORMAT_MASK_LOV")};this.controls.col_lov=function(D){this.get("COL_LOV",D)};this.controls.filter=function(D){this.get("SHOW_FILTER",D)};this.controls.filter2=function(){B.controls.set=true;this.get("SHOW_FILTER")};this.controls.display_column=function(D){this.get("SHOW_COLUMN",D)};this.controls.search_column=function(D){this.get("SEARCH_COLUMN",D)};this.controls.highlight=function(D){this.get("SHOW_HIGHLIGHT",D)};this.controls.ordering=function(D){this.get("SHOW_ORDERING",D)};this.controls.save=function(D){this.get("SAVE_REPORT",D)};this.controls.chart=function(D){this.get("SHOW_CHART",D)};this.controls.calendar=function(D){this.get("SAVE_CAL",D)};this.controls.aggregate=function(D){this.get("SHOW_AGGREGATE",D)};this.controls.delete_report=function(D){this.get("SHOW_DELETE",D)};this.controls.flashback=function(D){this.get("SHOW_FLASHBACK",D)};this.controls.reset=function(D){this.get("SHOW_RESET",D)};this.controls.rename=function(D){this.get("SHOW_RENAME",D)};this.controls.row=function(D){this.get("SHOW_DETAIL",D)};this.controls.computation=function(D){D=(D)?D:B.dialog.id;this.get("SHOW_COMPUTATION",D);B.dialog.id=false};this.controls.download=function(D){this.get("SHOW_DOWNLOAD",D)};this.controls.info=function(D){this.get("INFO",B.current_col_id)};this.controls.ctrl_break=function(D){this.get("SHOW_CTRL_BREAK",D)};this.highlight={};this.highlight.clear=function(D){B.action("CLEAR_HIGHLIGHT",false,$nvl(D,$x("HIGHLIGHT_ID").value))};this.highlight.toggle=function(E,D){B.action("TOGGLE_HIGHLIGHT",false,D,(E.checked)?"Y":"N")};this.highlight.save=function(F){var D=B.dialog.validate();if(!D){return }B.supress_update=true;var E=["apexir_HIGHLIGHT_ID","apexir_HIGHLIGHT_NAME",D.col,D.col_opt,D.form_items[0],D.form_items[1],"apexir_EXPR3","apexir_HIGHLIGHT_SEQUENCE","apexir_HIGHLIGHT_ENABLED","apexir_HIGHLIGHT_TYPE","apexir_BG_COLOR","apexir_FONT_COLOR"];B.get.AddArrayItems(E,1);B.action("SAVE_HIGHLIGHT");D=null;return };this.navigate={};this.navigate.paginate=function(D){B.get.addParam("p_widget_num_return",D.split("max_rows=")[1].split("rows_fetched")[0]);B.action("PAGE",D)};this.column={};this.column.pOb=this;this.column.break_on=function(D){if(D){B.current_col_id=D}B.action("BREAK",false,B.current_col_id)};this.column.break_toggle=function(E,D){B.action("BREAK_TOGGLE",false,D,(E.checked)?"Y":"N")};this.column.hide=function(E){var D=(!!E)?E:B.current_col_id;B.action("HIDE",false,D)};this.column.filter=function(G){var D="ADD";if(G){B.current_col_id=G;D="UPDATE"}else{G=$v("apexir_COLUMN_NAME")}var E=B.dialog.validate();if(!E){return }var F=[E.col,E.col_opt,E.form_items[0],E.form_items[1],"apexir_EXPR3"];B.get.AddArrayItems(F,1);B.supress_update=true;B.action("FILTER",D,G)};this.column.filter_delete=function(D){B.action("FILTER_DELETE",false,D)};this.column.filter_toggle=function(E,D){B.action("FILTER_TOGGLE",false,D,(E.checked)?"Y":"N")};this.column.display=function(F){var E=[];var D=$x("apexir_shuttle2").getElementsByTagName("SELECT")[0];for(i=0,len=D.options.length;i<len;i++){E[E.length]=D.options[i].value}B.get.AddArray(E,1);B.action("SET_COLUMNS")};this.column.order=function(I){if(I=="ASC"||I=="DESC"){B.get.addParam("f01",B.last_col_id);B.get.addParam("f02",I);B.action("COLUMN_ORDER")}else{var H=["apexir_COLUMN_01","apexir_COLUMN_02","apexir_COLUMN_03","apexir_COLUMN_04","apexir_COLUMN_05","apexir_COLUMN_06"];var G=["apexir_ASCENDING_01","apexir_ASCENDING_02","apexir_ASCENDING_03","apexir_ASCENDING_04","apexir_ASCENDING_05","apexir_ASCENDING_06"];var F=["apexir_NULLS_01","apexir_NULLS_02","apexir_NULLS_03","apexir_NULLS_04","apexir_NULLS_05","apexir_NULLS_06"];for(var E=0,D=H.length;E<D;E++){if($x(H[E]).value!="0"){B.get.addParam("f01",$v(H[E]));B.get.addParam("f02",$v(G[E]));B.get.addParam("f03",$v(F[E]))}}B.action("SORT")}};this.column.break_save=function(H){if(H=="ENABLE"||H=="DISABLE"){B.get.addParam("f01",B.last_col_id);B.get.addParam("f02",H)}else{var G=["apexir_COLUMN_01","apexir_COLUMN_02","apexir_COLUMN_03","apexir_COLUMN_04","apexir_COLUMN_05","apexir_COLUMN_06"];var F=["apexir_ENABLE_01","apexir_ENABLE_02","apexir_ENABLE_03","apexir_ENABLE_04","apexir_ENABLE_05","apexir_ENABLE_06"];for(var E=0,D=G.length;E<D;E++){if($x(G[E]).value!="0"){B.get.addParam("f01",$v(G[E]));B.get.addParam("f02",$v(F[E]))}}}B.action("SAVE_BREAK")};this.flashback={};this.flashback.save=function(){B.supress_update=true;B.action("FLASHBACK_SET",false,false,$v("apexir_FLASHBACK_TIME"))};this.flashback.clear=function(){B.action("FLASHBACK_CLEAR",false,false,false)};this.flashback.toggle=function(){B.action("FLASHBACK_TOGGLE",false,false,false)};this.aggregate={};this.aggregate.control=function(){if($v("apexir_AGGREGATE_BY")=="COUNT"){$x_Show_Hide("ALL_COLUMNS","NUMBER_COLUMNS")}else{$x_Show_Hide("NUMBER_COLUMNS","ALL_COLUMNS")}};this.aggregate.save=function(){var D=($v("apexir_AGGREGATE_BY")=="COUNT")?$v("apexir_COLUMN_NAME_ALL"):$v("apexir_COLUMN_NAME");B.action("SAVE_AGGREGATE",false,$v("apexir_AGGREGATION"),$v("apexir_AGGREGATE_BY"),D)};this.aggregate.clear=function(){B.action("DELETE_AGGREGATE",false,$v("apexir_AGGREGATION"))};this.aggregate.toggle=function(){};this.computation={};this.computation.save=function(){var D=$f_get_emptys(["apexir_COLUMN_LABEL","apexir_COMPUTATION_EXPR"],"error","");if(!!D){return }B.supress_update=true;B.action("SAVE_COMPUTATION",false,$v("apexir_COMPUTATION_ID"),$v("apexir_COLUMN_LABEL"),$v("apexir_REPORT_LABEL"),$v("apexir_FORMAT_MASK"),$v("apexir_COMPUTATION_EXPR"))};this.computation.clear=function(){B.action("DELETE_COMPUTATION",false,$v("apexir_COMPUTATION_ID"))};this.computation.toggle=function(){};this.chart={};this.chart.control=function(E){var D=[];D[0]=$x("apexir_LABEL_AXIS_TITLE").parentNode;D[1]=$x("apexir_VALUE_AXIS_TITLE").parentNode;D[2]=D[0].previousSibling;D[3]=D[1].previousSibling;if($x("apexir_CHART_TYPE_2").checked){$x_Hide(D)}else{$x_Show(D)}};this.chart.save=function(){var D=["apexir_CHART_TYPE","apexir_CHART_LABEL","apexir_CHART_VALUE","apexir_AGGREGATE_BY","apexir_LABEL_AXIS_TITLE","apexir_VALUE_AXIS_TITLE","apexir_SORT"];B.get.AddArrayItems(D,1);B.action("SAVE_CHART")};this.chart.clear=function(){B.action("DELETE_CHART")};this.chart.view=function(){B.action("VIEW_CHART",false,false)};this.calendar={};this.calendar.save=function(){B.action("SAVE_CALENDAR",false,false,$v("DATE_COLUMN"),$v("DISPLAY_COLUMN"))};this.calendar.view=function(){B.action("VIEW_CALENDAR",false,false)};this.data={};this.data.view=function(){B.action("VIEW_REPORT",false,false)};this.detail={};this.detail.last_row_opened=false;this.detail.show=function(D){if(B.ajax_busy){return }$x_Show(getElementsByClass("displayed"));$x_Show(getElementsByClass("other"));if($x("apexir_EXCLUDE_NULL_0").checked&&$x("apexir_DISPLAY_OPTION").checked){$x_Hide(getElementsByClass("null"));$x_Hide(getElementsByClass("other"))}else{if($x("apexir_EXCLUDE_NULL_0").checked){$x_Hide(getElementsByClass("null"))}else{if($x("apexir_DISPLAY_OPTION").checked){$x_Hide(getElementsByClass("other"))}}}B.l_Action="CHANGE_DETAIL_OPTION";B.supress_update=true;B.action("CHANGE_DETAIL_OPTION",false,false,$v("apexir_EXCLUDE_NULL_0"),$v("apexir_DISPLAY_OPTION"))};this.remove=function(){B.action("DELETE",false,false,false)};this.reset=function(){B.action("RESET",false,false,false)};this.save=function(F){var E=$x("create_category");var D=$f_get_emptys(["apexir_WORKSHEET_NAME"],"error","");if(E){D=$f_get_emptys([E],"error","")}if(!!D){return }l_cat=(E)?E.value:$v("apexir_WORKSHEET_CATEGORY");F=(!!F)?"RENAME":"SAVE";B.action(F,false,false,$v("apexir_WORKSHEET_NAME"),l_cat,$v("apexir_PUBLIC"),$v("apexir_DESCRIPTION"),$v("apexir_IS_DEFAULT"))};this.save_default=function(){B.action("SAVE_DEFAULT",false,false,false)};this.save_category_check=function(D){if(D.value=="new"){$dom_AddInput(D.parentNode,"text","create_category","")}else{$x_Remove("create_category")}};this.pull=function(D){if(!!D){B.report_id=D}B._Get("PULL")};this.download=function(D){};this.search=function(I){var H=B.item.search();var E=B.item.search_column();var F=$v("apexir_REPORT_ID");var G;if(I="SEARCH"){B.get.addParam("p_widget_num_return",$v("apexir_NUM_ROWS"));if(!!B.external_items){var D=B.external_items.split(",");B.get.AddPageItems(D)}}if($v_IsEmpty(H)){B.pull(F)}else{if(I="SEARCH"){G=[$v("apexir_CURRENT_SEARCH_COLUMN"),"contains",$v(H),$v("apexir_NUM_ROWS")];I="QUICK_FILTER"}else{G=[this.current_col_id,"=",$v(H),$v("apexir_NUM_ROWS")];I="FILTER"}B.get.AddArray(G,1);B.action(I,"ADD")}$s(H,"")};this.valid_action=function(D){if(D=="true"){B.pull()}else{$s("apexir_DIALOG_MESSAGE",D)}};this.action=function(M,D,J,L,K,I,H,G,E,F){B.l_Action=M;B._Get("ACTION",M,D,J,L,K,I,H,G,E,F)};this._Get=function(H,N,D,K,M,L,J,I,G,E,F){if(B.ajax_busy){return }else{B.ajax_busy=true}B.get.addParam("p_widget_name","worksheet");(!!H)?B.get.addParam("p_widget_mod",H):null;(!!N)?B.get.addParam("p_widget_action",N):null;(!!D)?B.get.addParam("p_widget_action_mod",D):null;B.get.addParam("x01",$v("apexir_WORKSHEET_ID"));(!!B.report_id)?B.get.addParam("x02",B.report_id):"0";(!!K)?B.get.addParam("x03",K):null;(!!M)?B.get.addParam("x04",M):null;(!!L)?B.get.addParam("x05",L):null;(!!J)?B.get.addParam("x06",J):null;(!!I)?B.get.addParam("x07",I):null;(!!G)?B.get.addParam("x08",G):null;(!!E)?B.get.addParam("x09",E):null;(!!F)?B.get.addParam("x10",F):null;B.get.GetAsync(B._Return)};this._Loading=function(){$x_Hide("apexir_rollover");B._BusyGraphic(p.readyState);return };this._Finished_Loading=function(){B._BusyGraphic(p.readyState);if(ie){if(!!!B.lCSS){B.lCSS=document.createStyleSheet()}startTag='<style id="apexir_WORKSHEET_CSS" type="text/css">';endTag="</style>";var F=p.responseText.indexOf(startTag);var E=p.responseText.substring(F+startTag.length);var D=E.indexOf(endTag);E=E.substring(0,D);B.lCSS.cssText=E}document.onclick=null;B.init();if(!!B.l_LastFunction){B.l_LastFunction();B.l_LastFunction=false}B.ajax_busy=false;return };this._BusyGraphic=function(D){if(D==1){$x_Show("apexir_LOADER")}else{$x_Hide("apexir_LOADER")}return };this._Return=function(){if(p.readyState==1){B._Loading()}else{if(p.readyState==2){}else{if(p.readyState==3){}else{if(p.readyState==4){$x_Hide("searchdrop").innerHTML="";if(B.l_Action=="CONTROL"){if(B.current_control=="SORT_WIDGET"){var I=$u_eval("("+p.responseText+")");B.dialog.id=I.dialog.id;var E=$x("apexir_rollover").getElementsByTagName("TABLE")[0];$x_Show_Hide([E,"apexir_sortup","apexir_sortdown","apexir_removefilter","apexir_hide","apexir_break","apexir_info","apexir_computation"],I.dialog.hide);$x_Hide(I.dialog.hide);if(I.dialog.uv){var J=I.dialog.row.length;for(var G=0;G<J;G++){if(I.dialog.row[G].R!=null){if(I.dialog.row[G].D!=null){var H=I.dialog.row[G].D}else{var H=I.dialog.row[G].R}var M=$dom_AddTag("apexir_rollover_content","a",H);M.apexir_RETURN_VALUE=I.dialog.row[G].R;M.href="javascript:void(false);";M.onclick=function(){var P=[B.current_col_id,"=",this.apexir_RETURN_VALUE,"",""];B.get.AddArray(P,1);B._Get("ACTION","QUICK_FILTER")}}}if(J>10){$x_Style("apexir_rollover_content","height","210")}else{$x_Style("apexir_rollover_content","height","")}$x_Show("apexir_rollover_content");$s("apexir_search","")}else{$x_Hide("apexir_rollover_content")}var F=$x(B.current_col_dom);$x_Show("apexir_rollover");$x_Style("apexir_rollover","left",findPosX(F.parentNode));$x_Style("apexir_rollover","top",findPosY(F)+F.offsetHeight+5);$x_Class(F.parentNode,"current");B.last_col_id=F.id;document.body.onclick=B.dialog.check}else{if(B.current_control=="SHOW_FILTER"||B.current_control=="SHOW_HIGHLIGHT"){var D=B.item.control_panel_drop();$s(D,p.responseText);$x_Show(D);ws_ColumnCheck($x("apexir_COLUMN_NAME"))}else{if(B.current_control=="NARROW"||B.current_control=="FORMAT_MASK_LOV"){var K=new $d_LOV_from_JSON();K.l_Type="a";K.create(O,p.responseText);K.l_Dom.id="apexir_col_values_drop";$x_Style(K.l_Dom,"height","200px");$x_Style(K.l_Dom,"display","block");if(!$x("apexir_col_values_drop_space")){lThis=$dom_AddTag($x(B.temp_return_element).parentNode,"BR");lThis.id="apexir_col_values_drop_space"}$x(B.temp_return_element).parentNode.appendChild(K.l_Dom);for(var G=0,J=K.l_NewEls.length;G<J;G++){M=K.l_NewEls[G];M.href="javascript:void(false)";if(B.current_control=="NARROW"){M.onclick=function(){var P=$x("apexir_COLUMN_NAME");var Q=$x("apexir_"+P.options[P.selectedIndex].className+"_OPT");var R=$v(Q);if(R=="in"||R=="not in"){R=$v(B.temp_return_element);$s(B.temp_return_element,(isEmpty(B.temp_return_element))?this.id:R+","+this.id)}else{$s(B.temp_return_element,this.id);$x_Remove("apexir_col_values_drop")}}}else{M.onclick=function(){$s(B.temp_return_element,this.id);$x_Remove("apexir_col_values_drop")}}}B.supress_update=false;B.l_Action=false;document.body.onclick=B.dialog.check2}else{if(B.current_control=="INFO"){var E=$x("apexir_rollover").getElementsByTagName("TABLE")[0];$x_Hide(E);$s("apexir_rollover_content",p.responseText);$x_Style("apexir_rollover_content","height","");var F=$x(B.current_col_dom);$x_Show("apexir_rollover");$x_Style("apexir_rollover","left",findPosX(F.parentNode));$x_Style("apexir_rollover_content","top",findPosY(F)+F.offsetHeight+5);$x_Class(F.parentNode,"current");B.last_col_id=F.id;document.body.onclick=B.dialog.check}else{if(B.current_control=="SEARCH_COLUMN"){var O=$x("apexir_SEARCHDROP");$s(O,"");var K=new $d_LOV_from_JSON();K.l_Type="a";K.create(O,p.responseText);K.l_Dom.id="apexir_columnsearch";var L=B.item.search_column();for(var G=0,J=K.l_NewEls.length;G<J;G++){K.l_NewEls[G].href="javascript:void()";K.l_NewEls[G].onclick=function(){if(this.id!=0){$s("apexir_SEARCH_COLUMN_DROP",this.innerHTML+"");$s(L,this.id)}else{$s("apexir_SEARCH_COLUMN_DROP","");$s(L,"")}$x_Hide(O)}}$x_Show(O);document.body.onclick=B.dialog.check}else{if(B.current_control=="SHOW_DETAIL"){$x_Hide(B.item.worksheet_report());$s("apexir_DATA_PANEL","");$s("apexir_CONTROL_PANEL_DROP","");$s(B.item.worksheet_detail(),p.responseText);$x_Show(B.item.worksheet_detail())}else{var D=B.item.control_panel_drop();D.innerHTML=p.responseText;if(B.current_control=="SHOW_CHART"){B.chart.control()}if(B.controls.set){$s("apexir_COLUMN_NAME",B.last_col_id);$s("apexir_EXPR",$v("apexir_SEARCH"));B.controls.set=false;$s("apexir_SEARCH","")}if(B.current_control=="SHOW_COLUMN"){window.g_Shuttlep_v01=null;if(!N){var N=[]}N[2]=$x("apexir_SHUTTLE_LEFT");N[1]=$x("apexir_SHUTTLE_RIGHT");window.g_Shuttlep_v01=new dhtml_ShuttleObject(N[2],N[1])}$x_Show(D)}}}}}}}else{if(B.supress_update){if(B.l_Action=="SAVE_COMPUTATION"||B.l_Action=="FLASHBACK_SET"||B.l_Action=="SAVE_HIGHLIGHT"||B.l_Action=="FILTER"){B.l_LastFunction=function(){B.valid_action(p.responseText)}}B.ajax_busy=false;B.supress_update=false;B.l_Action=false}else{B.ajax_busy=false;$x_Hide("apexir_rollover");M=$x("apexir_WORKSHEET");M.id="apexir_WORKSHEET_old";lThis=$u_js_temp_drop();$s(lThis,p.responseText);M.parentNode.replaceChild($x("apexir_WORKSHEET"),M);$d_ClearAndHide("apexir_DETAIL");$s("apexir_CURRENT_SEARCH_COLUMN","");$s("apexir_SEARCH_COLUMN_DROP","");$x_Show("apexir_REPORT");$u_js_temp_clear()}}B._Finished_Loading()}else{return false}}}}};return ;function C(D){this.l_Action=false;this.l_Type=false;B.ajax_busy=false;if(!!D){B.worksheet_id=D}B.report_id=($v("apexir_REPORT_ID"))?$v("apexir_REPORT_ID"):"0";this.get=new htmldb_Get(null,$v("pFlowId"),"APXWGT",$v("pFlowStepId"))}}};apex.ajax={clob:function(B){var D=this;this.ajax=new htmldb_Get(null,$x("pFlowId").value,"APXWGT",0);this.ajax.addParam("p_widget_name","apex_utility");this.ajax.addParam("x04","CLOB_CONTENT");this._get=C;this._set=A;this._return=!!B?B:E;return ;function C(F){D.ajax.addParam("x05","GET");D.ajax.GetAsync(D._return)}function A(F){D.ajax.addParam("x05","SET");D.ajax.AddArrayClob(F,1);D.ajax.GetAsync(D._return)}function E(){if(p.readyState==1){}else{if(p.readyState==2){}else{if(p.readyState==3){}else{if(p.readyState==4){return p}else{return false}}}}}},test:function(B){var D=this;this.ajax=new htmldb_Get(null,$x("pFlowId").value,"APXWGT",0);this.ajax.addParam("p_widget_name","apex_utility");this._get=C;this._set=A;this._return=!!B?B:E;return ;function C(F){D.ajax.GetAsync(D._return)}function A(F){}function E(F){}},widget:function(C,B){var E=this;this.ajax=new htmldb_Get(null,$x("pFlowId").value,"APXWGT",0);this.ajax.addParam("p_widget_name",C);this._get=D;this._set=A;this._return=!!B?B:F;return ;function D(G){E.ajax.GetAsync(E._return)}function A(G){}function F(G){}},ondemand:function(C,B){var E=this;this.ajax=new htmldb_Get(null,$x("pFlowId").value,"APPLICATION_PROCESS="+C,0);this._get=D;this._set=A;this._return=!!B?B:F;return ;function D(G){E.ajax.GetAsync(E._return)}function A(G){}function F(G){}}};apex.tabular={table:function(H,E){var D=this;this.l_Table=$x(H);this.l_Headers=D.l_Table.rows[0].cells;this.l_Row1=D.l_Table.rows[1].cells;this.currentItem=null;this.col={};this.col.length=D.l_Headers.length;this.col.by_id=function(K){for(var J=0,I=D.col.length;J<I;J++){var L=(D.col[J].id==K);if(L){return D.col[J]}}};this.col.by_name=function(J){for(var K=0,I=D.col.length;K<I;K++){var L=(D.col[K].name==J);if(L){return D.col[K]}}};this.error=function(){D.currentItem.focus();$x_Style(D.currentItem,"border","1px solid red")};this.success=function(){$x_Style(D.currentItem,"border","")};for(var C=0,A=this.l_Headers.length;C<A;C++){var B={};B.id=this.l_Headers[C].id;var G=dtml_Return_Form_Items(this.l_Row1[C],"ALL")[0];B.name=(G)?G.name:false;B.dom=this.l_Headers[C];this.col[C]=B}var F=dtml_Return_Form_Items(this.l_Table,"ALL");for(var C=0,A=F.length;C<A;C++){F[C].onchange=function(){D.row.init(this,D)}}this.row={};this.row.dom;this.row.init=(E)?E:function(J,I){};this.row.cell={};this.row.cell.item=function(I){return $x_FormItems(D.row.dom.cells[D.col.by_id(I).dom.cellIndex])[0]};this.row.cell.value=function(J,I){D.row.cell.item(J)[0].value=I};this.row.cell.disable=function(J,I){$v(D.row.cell.item(J),I)}},sort:function(A){var B=this;B.class_name="pb";B.src_up=htmldb_Img_Dir+"htmldb/icons/up_arrow.gif";B.src_down=htmldb_Img_Dir+"htmldb/icons/down_arrow.gif";B.resequence=true;B.resequence_class="orderby";B.table=false;B.row={};B.row.before_move=function(){};B.row.after_move=function(){};B.row.up=function(D){return B.row.move(D,"UP")};B.row.down=function(D){return B.row.move(D,"DOWN")};B.row.top=function(){};B.row.bottom=function(){};B.row.move=function(H,D){B.row.before_move();var O=$x_UpTill(H,"TR");ie_RowFixStart(O);$tr_RowMoveFollow(O,true);var G=O.parentNode;var M=O.nextSibling;var J=O.previousSibling;if(D=="DOWN"){while(M!=null){if(M.nodeType==1){break}M=M.nextSibling}if(M!=null&&M.nodeName=="TR"){oElement=G.insertBefore(O,M.nextSibling)}else{oElement=G.insertBefore(O,G.getElementsByTagName("TR")[1])}}else{if(D=="UP"){while(J!=null){if(J.nodeType==1){break}J=J.previousSibling}if(J!=null&&J.firstChild!=null&&J.firstChild.nodeName!="TH"&&J.nodeName=="TR"){oElement=G.insertBefore(O,J)}else{oElement=G.appendChild(O)}}}ie_RowFixFinish(oElement);for(var I=1,L=G.rows.length;I<L;I++){var N=G.rows[I];var E=getElementsByClass(B.resequence_class,N,"INPUT");for(var F=0,K=E.length;F<K;F++){E[F].value=I}}B.row.after_move();return oElement};B.row.remove=function(){};B.row.add=function(){};B.init=C;if(!!A){B.init(A)}return ;function C(H){B.table=$x(H);B.lH=getElementsByClass(B.resequence_class,B.table,"INPUT");this.create=F;var E=B.table.rows;for(var G=0,D=E.length;G<D;G++){if(G==0){$tr_AddTH(E[G],"<br />")}else{var K=$tr_AddTD(E[G]);var J=this.create(K,B.src_up,B.class_name,function(){B.row.up(this)});var I=this.create(K,B.src_down,B.class_name,function(){B.row.down(this)})}}return ;function F(P,O,M,L){var N=$dom_AddTag(P,"IMG");N.src=O;N.className=M;N.onclick=L;return N}}}};function ws_OperatorCheck(D){var B=$x("apexir_COLUMN_NAME").options[$x("apexir_COLUMN_NAME").selectedIndex].className;var A=$v(D);var C=[];C[0]=$x("apexir_EXPR").parentNode;C[1]=$x("apexir_EXPR2").parentNode;C[2]=$x("apexir_EXPR3").parentNode;C[3]=$x("apexir_BETWEEN_FROM_fieldset").parentNode;C[4]=$x("apexir_BETWEEN_TO_fieldset").parentNode;$x_Show_Hide("apexir_EXPR_ICON",C);if(A=="is null"||A=="is not null"){$x_Hide("apexir_EXPRESSION_LABEL");return }else{$x_Show("apexir_EXPRESSION_LABEL");if(B=="DATE"&&!(A=="is in the last"||A=="is not in the last"||A=="is in the next"||A=="is not in the next")){$x_Show(C[3]);(A=="between")?$x_Show(C[4]):null}else{$x_Show(C[0]);(A=="between")?$x_Show(C[1]):null;(A=="is in the last"||A=="is not in the last"||A=="is in the next"||A=="is not in the next")?$x_Show_Hide(C[2],"apexir_EXPR_ICON"):null}}return }function ws_ColumnCheck(B){var A=B.options[B.selectedIndex].className;$x_HideSiblings("apexir_"+A+"_OPT");ws_OperatorCheck("apexir_"+A+"_OPT")}function item_menu(F,B){$x_Style("item_menu","position","absolute");var D=$x("item_menu").getElementsByTagName("a");for(var C=0,A=D.length;C<A;C++){var E=D[C].href;E=E.split(":");E[E.length-1]=B;D[C].href=$u_ArrayToString(E,":")}dhtml_ButtonDropDown(F,"item_menu");$x_Show("item_menu")}function comp(A){lSpace=(!(isNaN(A))||A==".");html_ReturnToTextSelection(A,"apexir_COMPUTATION_EXPR",lSpace)}function ajax_calendar(B,E,G){var I=$v("p_cal_type_field_id");var D=$v("p_cal_date_field_id");var F=$v("p_calendar_id");var A="calendar"+F;$s(D,$v("p_calendar_date"));var H=new apex.ajax.widget("calendar",function(){if(p.readyState==1){document.body.style.cursor="wait"}else{if(p.readyState==2){}else{if(p.readyState==3){}else{if(p.readyState==4){$x(A).innerHTML=p.responseText;$s(D,$v("p_calendar_date"));document.body.style.cursor=""}else{return false}}}}});if(B=="S"){B=$v("p_calendar_type")}else{$s(I,B)}H.ajax.addParam("p_widget_mod",B);H.ajax.addParam("p_widget_action",E);H.ajax.addParam("x01",F);var C=(!!G&&G!=="")?G:$v(D);H.ajax.add(D,C);H.ajax.addParam("x02",C);H.ajax.add(I,B);H._get()};