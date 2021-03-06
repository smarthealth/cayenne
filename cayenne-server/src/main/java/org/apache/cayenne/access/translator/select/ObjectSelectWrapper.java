/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.access.translator.select;

import java.util.Collection;
import java.util.Objects;

import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.property.BaseProperty;
import org.apache.cayenne.map.EntityResolver;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.PrefetchTreeNode;
import org.apache.cayenne.query.QueryMetadata;
import org.apache.cayenne.query.Select;

/**
 * @since 4.2
 */
public class ObjectSelectWrapper implements TranslatableQueryWrapper {

    private final ObjectSelect<?> selectQuery;

    public ObjectSelectWrapper(ObjectSelect<?> selectQuery) {
        this.selectQuery = Objects.requireNonNull(selectQuery);
    }

    @Override
    public boolean isDistinct() {
        return false;
    }

    @Override
    public QueryMetadata getMetaData(EntityResolver resolver) {
        return selectQuery.getMetaData(resolver);
    }

    @Override
    public PrefetchTreeNode getPrefetchTree() {
        return selectQuery.getPrefetches();
    }

    @Override
    public Expression getQualifier() {
        return selectQuery.getWhere();
    }

    @Override
    public Collection<Ordering> getOrderings() {
        return selectQuery.getOrderings();
    }

    @Override
    public Collection<BaseProperty<?>> getColumns() {
        return null;
    }

    @Override
    public Expression getHavingQualifier() {
        return null;
    }

    @Override
    public Select<?> unwrap() {
        return selectQuery;
    }
}
