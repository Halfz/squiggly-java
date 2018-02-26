package com.github.bohnman.squiggly.core.parser;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.bohnman.core.lang.CoreAssert.notNull;

public class FunctionNode {

    private final ParseContext context;
    private final String name;
    private final List<ArgumentNode> parameters;
    private final boolean ignoreNulls;
    private final FunctionNodeType type;
    private final boolean ascending;

    public FunctionNode(ParseContext context, String name, List<ArgumentNode> parameters, boolean ignoreNulls, FunctionNodeType type, boolean ascending) {
        this.context = notNull(context);
        this.name = notNull(name);
        this.parameters = Collections.unmodifiableList(notNull(parameters));
        this.ignoreNulls = ignoreNulls;
        this.type = notNull(type);
        this.ascending = ascending;
    }

    public ParseContext getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public List<ArgumentNode> getParameters() {
        return parameters;
    }

    public boolean isIgnoreNulls() {
        return ignoreNulls;
    }

    public FunctionNodeType getType() {
        return type;
    }

    public boolean isAscending() {
        return ascending;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name, parameters);
    }

    public static class Builder {

        @Nullable
        private ParseContext context;

        @Nullable
        private String name;

        private List<ArgumentNode> parameters = new ArrayList<>();
        private boolean ignoreNulls;

        private FunctionNodeType type = FunctionNodeType.FUNCTION;
        private boolean ascending = true;

        private Builder() {
        }

        public Builder context(ParseContext context) {
            this.context = context;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parameter(ArgumentNode.Builder arg) {
            int index = parameters.size();
            parameters.add(arg.index(index).build());
            return this;
        }

        public Builder ignoreNulls(boolean ignore) {
            this.ignoreNulls = ignore;
            return this;
        }

        public Builder type(FunctionNodeType type) {
            this.type = type;
            return this;
        }

        public Builder ascending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        public FunctionNode build() {
            return new FunctionNode(context, name, parameters, ignoreNulls, type, ascending);
        }
    }
}
