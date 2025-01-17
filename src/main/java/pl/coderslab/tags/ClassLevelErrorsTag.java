package pl.coderslab.tags;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;


public class ClassLevelErrorsTag extends ErrorsTag {
    private boolean firstErrorOnly = true;

    public boolean isFirstErrorOnly() {
        return firstErrorOnly;
    }

    public void setFirstErrorOnly(boolean firstErrorOnly) {
        this.firstErrorOnly = firstErrorOnly;
    }

    @Override
    protected void renderDefaultContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag(getElement());
        writeDefaultAttributes(tagWriter);
        String delimiter = ObjectUtils.getDisplayString(evaluate("delimiter", getDelimiter()));
        String[] errorMessages = getBindStatus().getErrorMessages();
        for (int i = 0; i < errorMessages.length; i++) {
            String errorMessage = errorMessages[i];
            if (i > 0) {
                tagWriter.appendValue(delimiter);
            }
            tagWriter.appendValue(getDisplayString(errorMessage));
            if (firstErrorOnly) break;
        }
        tagWriter.endTag();
    }
}
