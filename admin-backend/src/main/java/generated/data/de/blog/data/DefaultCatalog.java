/*
 * This file is generated by jOOQ.
 */
package de.blog.data;


import java.util.Arrays;
import java.util.List;

import org.jooq.Constants;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultCatalog extends CatalogImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_CATALOG</code>
     */
    public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();

    /**
     * The schema <code>public</code>.
     */
    public final Public PUBLIC = Public.PUBLIC;

    /**
     * No further instances allowed
     */
    private DefaultCatalog() {
        super("");
    }

    @Override
    public final List<Schema> getSchemas() {
        return Arrays.asList(
            Public.PUBLIC
        );
    }

    /**
     * A reference to the 3.18 minor release of the code generator. If this
     * doesn't compile, it's because the runtime library uses an older minor
     * release, namely: 3.18. You can turn off the generation of this reference
     * by specifying /configuration/generator/generate/jooqVersionReference
     */
    private static final String REQUIRE_RUNTIME_JOOQ_VERSION = Constants.VERSION_3_18;
}
