package org.dou.mybatissqlrun.runsql;

import com.alibaba.fastjson2.JSON;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.psi.PsiElement;

import java.awt.event.MouseEvent;

/**
 * @author yiminlin
 * @date 2022/02/01 7:28 下午
 **/
public class SqlGenerateNavigationHandler implements GutterIconNavigationHandler<PsiElement> {

	@Override
	public void navigate(MouseEvent e, PsiElement elt) {

		System.out.println("e：" + JSON.toJSONString(e));
		System.out.println("e：" + JSON.toJSONString(elt));

	}


}
