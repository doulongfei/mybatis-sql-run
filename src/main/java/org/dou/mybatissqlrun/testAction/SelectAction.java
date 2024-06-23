package org.dou.mybatissqlrun.testAction;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;

public class SelectAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		// 获取到编辑器设置
		EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();
		// 对之前的状态取反
		editorSettingsExternalizable.setCamelWords(!editorSettingsExternalizable.isCamelWords());

		System.out.println("发生切换");
	}
}
