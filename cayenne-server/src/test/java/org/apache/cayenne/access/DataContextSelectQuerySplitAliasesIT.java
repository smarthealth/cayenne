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
package org.apache.cayenne.access;

import org.apache.cayenne.di.Inject;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.test.jdbc.DBHelper;
import org.apache.cayenne.test.jdbc.TableHelper;
import org.apache.cayenne.testdo.testmap.Artist;
import org.apache.cayenne.unit.di.server.CayenneProjects;
import org.apache.cayenne.unit.di.server.ServerCase;
import org.apache.cayenne.unit.di.server.UseServerRuntime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

@UseServerRuntime(CayenneProjects.TESTMAP_PROJECT)
public class DataContextSelectQuerySplitAliasesIT extends ServerCase {

    @Inject
    private DataContext context;

    @Inject
    private DBHelper dbHelper;

    private TableHelper tArtist;
    private TableHelper tPainting;

    @Before
    public void setUp() throws Exception {
        tArtist = new TableHelper(dbHelper, "ARTIST");
        tArtist.setColumns("ARTIST_ID", "ARTIST_NAME");

        tPainting = new TableHelper(dbHelper, "PAINTING");
        tPainting.setColumns("PAINTING_ID", "ARTIST_ID", "PAINTING_TITLE");
    }

    private void createTwoArtistsTwoPaintingsDataSet() throws Exception {

        tArtist.insert(1, "AA");
        tArtist.insert(2, "BB");

        tPainting.insert(1, 1, "X");
        tPainting.insert(2, 2, "Y");
    }

    private void createTwoArtistsThreePaintingsDataSet() throws Exception {

        createTwoArtistsTwoPaintingsDataSet();
        tPainting.insert(3, 2, "X");
    }

    @Test
    public void testAliasPathSplits_SinglePath() throws Exception {
        createTwoArtistsTwoPaintingsDataSet();

        SelectQuery<Artist> query = SelectQuery.query(Artist.class);
        query.andQualifier(ExpressionFactory.matchExp("p.paintingTitle", "X"));

        query.aliasPathSplits("paintingArray", "p");

        List<Artist> artists = query.select(context);
        assertEquals(1, artists.size());
        assertEquals("AA", artists.get(0).getArtistName());
    }

    @Test
    public void testAliasPathSplits_SplitJoin() throws Exception {
        createTwoArtistsThreePaintingsDataSet();

        SelectQuery<Artist> query = SelectQuery.query(Artist.class);
        query.andQualifier(ExpressionFactory.matchExp("p1.paintingTitle", "X"));
        query.andQualifier(ExpressionFactory.matchExp("p2.paintingTitle", "Y"));

        query.aliasPathSplits("paintingArray", "p1", "p2");

        List<Artist> artists = query.select(context);
        assertEquals(1, artists.size());
        assertEquals("BB", artists.get(0).getArtistName());
    }
}
