/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package sample.ejb3;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * HelloworldService EJB implementation that accesses Helloworld SCA services through injected references.
 */
@Stateless(name="HelloworldBean", mappedName="HelloworldBeanMappedName")
public class HelloworldServiceBean implements HelloworldService, HelloworldLocalService {
    
    @EJB(name="languageService")
    LanguageService langService;
    
    @EJB
    LanguageLocalService langLocalService;
    
    @Resource(name="jdbc/ds")
    private DataSource ds;
    
    @Resource(name="country")
    private String country;
    
    @Resource
    SessionContext ctx;
    
    public void postConstruct() {
    	System.out.println("HelloworldServiceBean.postConstruct()");
    }
    
    public String getGreetings(String name) {
    	System.out.println("HelloworldServiceBean.country = "+country);
    	System.out.println("HelloworldServiceBean.ds = "+ds);
        String lang = langService.getLanguage(name);
        if(lang.equalsIgnoreCase("english")) {
            return "Hello "+name;
        } else if(lang.equalsIgnoreCase("telugu")) {
            return "Namaskaram "+name;
        } else if(lang.equalsIgnoreCase("hindi")) {
            return "Namaskar "+name;
        } else {
            return "Hello "+name;
        }
    }
}
