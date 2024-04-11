package pl.coderslab.tags;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.ErrorsTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;


public class ClassLevelErrorsTag extends ErrorsTag {
    private String chooseField;

    public String getChooseField() {
        return chooseField;
    }

    public void setChooseField(String chooseField) {
        this.chooseField = chooseField;
    }

    @Override
    protected void renderDefaultContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag(getElement());
        writeDefaultAttributes(tagWriter);
        String delimiter = ObjectUtils.getDisplayString(evaluate("delimiter", getDelimiter()));
        String[] errorMessages = getBindStatus().getErrorMessages();
        String errorPrefix = String.format("%s:", chooseField);

        for (int i = 0; i < errorMessages.length; i++) {
            String errorMessage = errorMessages[i];
            if (errorMessage.startsWith(errorPrefix)) {
                tagWriter.appendValue(errorMessage.replace(errorPrefix, "").trim());
                if (i > 0) {
                    tagWriter.appendValue(delimiter);
                }
            }
        }
        tagWriter.endTag(true);
    }
}
