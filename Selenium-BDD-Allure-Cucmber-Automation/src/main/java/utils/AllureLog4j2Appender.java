package utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;

@Plugin(name = "AllureAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)
public class AllureLog4j2Appender extends AbstractAppender {

    protected AllureLog4j2Appender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout, true, null);
    }

    @PluginFactory
    public static AllureLog4j2Appender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {
        return new AllureLog4j2Appender(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        Allure.addAttachment("Log Entry",
                "text/plain",
                event.getMessage().getFormattedMessage());
    }
}