package org.dou.mybatissqlrun.runsql;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import net.sf.jsqlparser.schema.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyBatisLineMarkerProvider implements LineMarkerProvider {

	public static final List<String> MYBATIS_OPS = Lists.newArrayList(
			"insert", "update", "delete", "select"
	);

	private void handleIconClick(PsiElement element) {
		// 在这里处理点击事件的逻辑
		XmlTag xmlTag = (XmlTag) element;





		System.out.println("点击：" + xmlTag.getName() + "text:" + xmlTag.getText());
	}

	@Nullable
	@Override

	public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
		if (element instanceof XmlTag xmlTag) {
			if (MYBATIS_OPS.contains(xmlTag.getName())) {
				// 定义点击事件处理器
				GutterIconNavigationHandler<PsiElement> navigationHandler = (e, elt) -> handleIconClick(elt);
				// 更新 LineMarkerInfo 的构造以包含点击处理器
				return new LineMarkerInfo<>(
						element,
						element.getTextRange(),
						AllIcons.Actions.RunAll,
						(e) -> "使用模拟参数执行sql",
						navigationHandler,
						GutterIconRenderer.Alignment.RIGHT,
						() -> "MyBatis Mapper"
				);
			}
		}
		return null;
	}

}
