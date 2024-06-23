package org.dou.mybatissqlrun.runsql;

import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.Function;


/**
 * @author yiminlin
 * @date 2022/01/31 10:36 上午
 **/
public class FunctionTooltip implements Function<PsiElement, String> {

    PsiElement psiElement;

    public FunctionTooltip() {}

    public FunctionTooltip(PsiElement psiElement) {
        this.psiElement = psiElement;
    }

    @Override
    public String fun(PsiElement psiElement) {
        String MSG = "Generate Sql and params for ";
        if (psiElement instanceof XmlTag) {
            XmlAttribute attribute = ((XmlTag) psiElement).getAttribute("id");
            if (attribute == null) {
                return null;
            }

            return MSG + attribute.getValue();
        }
        return null;
    }
}
