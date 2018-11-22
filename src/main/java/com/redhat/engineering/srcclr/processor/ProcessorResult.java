/*
 * Copyright (C) 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.engineering.srcclr.processor;

import com.redhat.engineering.srcclr.json.sourceclear.Library;
import com.redhat.engineering.srcclr.json.sourceclear.Metadata_;
import com.redhat.engineering.srcclr.json.sourceclear.Vulnerability;
import lombok.Getter;
import lombok.Setter;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

@Getter
@Setter
public class ProcessorResult
{
    private Boolean notify = false;
    private Boolean fail = false;
    private String message = "";
    private Vulnerability vulnerability;
    private Library library;
    private String scanReport;

    public void setVulnerability ( Vulnerability vulnerability )
    {
        this.vulnerability = vulnerability;

        if ( isNotEmpty( message ))
        {
            String sb = message
                            + System.lineSeparator()
                            + "Original SourceClear warning:"
                            + System.lineSeparator()
                            + vulnerability.getOverview();
            vulnerability.setOverview( sb );
        }
    }

    public void setScanReport( Metadata_ metadata )
    {
        scanReport = ( metadata != null && metadata.getReport() != null ) ? (String) metadata.getReport() : "<no-report-available>";
    }
}