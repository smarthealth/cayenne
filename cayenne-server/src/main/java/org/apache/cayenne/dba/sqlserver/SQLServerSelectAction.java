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
package org.apache.cayenne.dba.sqlserver;

import java.sql.Connection;

import org.apache.cayenne.access.jdbc.RowReaderFactory;
import org.apache.cayenne.access.jdbc.SelectAction;
import org.apache.cayenne.access.trans.SelectTranslator;
import org.apache.cayenne.dba.JdbcAdapter;
import org.apache.cayenne.map.EntityResolver;
import org.apache.cayenne.query.SelectQuery;

public class SQLServerSelectAction extends SelectAction {

    public <T> SQLServerSelectAction(SelectQuery<T> query, JdbcAdapter adapter, EntityResolver entityResolver,
            RowReaderFactory rowReaderFactory) {
        super(query, adapter, entityResolver, rowReaderFactory);
    }

    @Override
    protected SelectTranslator createTranslator(Connection connection) {
        SelectTranslator translator = new SQLServerSelectTranslator();
        translator.setQuery(query);
        translator.setAdapter(adapter);
        translator.setEntityResolver(getEntityResolver());
        translator.setConnection(connection);
        translator.setJdbcEventLogger(adapter.getJdbcEventLogger());
        return translator;
    }

}