package io.vertx.tp.plugin.shell.atom;

import io.vertx.up.util.Ut;
import org.apache.commons.cli.Options;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author <a href="http://www.origin-x.cn">lang</a>
 */
public class CommandInput implements Serializable {
    private final ConcurrentMap<String, String> inputValue = new ConcurrentHashMap<>();
    private Options options = new Options();

    private CommandInput(final List<String> names, final List<String> values) {
        if (values.size() >= names.size()) {
            // Fix: java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            Ut.itList(names, (name, index) -> {
                final String value = values.get(index);
                this.inputValue.put(name, value);
            });
        }
    }

    public static CommandInput create(final List<String> names, final List<String> values) {
        return new CommandInput(names, values);
    }

    public String get(final String name) {
        return this.inputValue.get(name);
    }

    public ConcurrentMap<String, String> get() {
        return this.inputValue;
    }

    public Options options() {
        return this.options;
    }

    public CommandInput bind(final Options options) {
        this.options = options;
        return this;
    }
}