package org.dou.mybatissqlrun.runsql;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.database.autoconfig.DataSourceRegistry;
import com.intellij.database.dataSource.DatabaseConnection;
import com.intellij.database.dataSource.DatabaseConnectionManager;
import com.intellij.database.dataSource.LocalDataSource;
import com.intellij.database.dataSource.LocalDataSourceManager;
import com.intellij.database.model.DasDataSource;
import com.intellij.database.psi.DataSourceManager;
import com.intellij.database.psi.DbDataSource;
import com.intellij.database.view.DatabaseView;
import com.intellij.database.view.ui.DataSourceManagerDialog;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import org.dou.mybatissqlrun.utils.DatabaseUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class MyBatisLineMarkerProvider implements LineMarkerProvider {

	public static final List<String> MYBATIS_OPS = Lists.newArrayList(
			"insert", "update", "delete", "select"
	);


	public void printDatabaseConnections(Project project) {

		LocalDataSourceManager instance = LocalDataSourceManager.getInstance(project);
		instance.getDataSources().forEach(dataSource -> {
			System.out.println("Data Source: " + dataSource.getName());
			System.out.println("URL: " + dataSource.getConnectionConfig().getUrl());
			System.out.println("User: " + dataSource.getConnectionConfig().getName());
			System.out.println("Password: " + dataSource.getPasswordStorage());
		});
	}

	private void handleIconClick(PsiElement element) {
		// 在这里处理点击事件的逻辑
		XmlTag xmlTag = (XmlTag) element;

		List<DasDataSource> allLocalDataSource = DatabaseUtil.getAllLocalDataSource(element.getProject());

		printDatabaseConnections(element.getProject());

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
