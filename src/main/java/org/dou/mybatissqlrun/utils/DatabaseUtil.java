package org.dou.mybatissqlrun.utils;

import com.intellij.database.model.DasDataSource;
import com.intellij.database.psi.DataSourceManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

	/**
	 * 获取所有本地数据源
	 *
	 * @param project 项目
	 * @return {@link List }<{@link DasDataSource }>
	 */
	public static List<DasDataSource> getAllLocalDataSource(Project project) {
		List<DasDataSource> dataSourceList = new ArrayList<>();
		List<DataSourceManager<?>> managers = DataSourceManager.getManagers(project);
		if (CollectionUtils.isNotEmpty(managers)) {
			for (DataSourceManager<?> manager : managers) {
				List<?> dataSources = manager.getDataSources();
				if (CollectionUtils.isNotEmpty(dataSources)) {
					for (Object dataSource : dataSources) {
						if (dataSource instanceof DasDataSource dasDataSource) {
							dataSourceList.add(dasDataSource);
						}
					}
				}
			}
		}
		return dataSourceList;
	}

}
