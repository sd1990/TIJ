package org.songdan.tij.string;

public class StringDemo {

    public static void main(String[] args) {
        String str1="abcdef";
        String str2="abcdef";
        String str3=new String(str1);
        String str4=new String(str1);
        System.out.println(str1==str2);
        System.out.println(str3==str4);
        //---------------------------
        System.out.println(unescapeBackslash("\\\\\\n\\t"));
        System.out.println(StringUtils.unescape_perl_string("\\\\\\n\\t"));
        String xml = "<interface>" +
                "  <globalInfo>" +
                "    <terminalCode>0</terminalCode>" +
                "    <appId>KJPT_PTDZFP</appId>" +
                "    <version>1.0</version>" +
                "    <interfaceCode>ECXML.FPKJ.QQ.E_INV_ASYN</interfaceCode>" +
                "    <userName>13209199</userName>" +
                "    <passWord>8BED3489A7a4he5fd3ohC6+ElruQwFjw==</passWord>" +
                "    <taxpayerId>150001661565717154</taxpayerId>" +
                "    <authorizationCode>6109014441</authorizationCode>" +
                "    <requestTime>2016-09-06 15:06:37</requestTime>" +
                "    <dataExchangeId>0944650920160906B22FA597E</dataExchangeId>" +
                "  </globalInfo>" +
                "  <returnStateInfo>" +
                "    <returnCode></returnCode>" +
                "    <returnMessage></returnMessage>" +
                "  </returnStateInfo>" +
                "  <data>" +
                "    <dataDescription>" +
                "      <zipCode>0</zipCode>" +
                "      <encryptCode>0</encryptCode>" +
                "      <codeType>0</codeType>" +
                "    </dataDescription>" +
                "    <content>PFJFUVVFU1RfRlBLSlhYIGNsYXNzPSJSRVFVRVNUX0ZQS0pYWCI+CiAgPEZQS0pYWF9GUFRYWCBj" +
                "bGFzcz0iRlBLSlhYX0ZQVFhYIj4KICAgIDxGUFFRTFNIPllTNjYwMzE2MDkwNjExNDg3OTEwPC9G" +
                "UFFRTFNIPgogICAgPEpTUVFMU0g+WVM2NjAyMTYwOTA2MTE0ODI3NDc8L0pTUVFMU0g+CiAgICA8" +
                "RFNQVEJNPjEzMTAwMDAxPC9EU1BUQk0+CiAgICA8TlNSU0JIPjE1MDAwMTY2MTU2NTcxNzE1NDwv" +
                "TlNSU0JIPgogICAgPE5TUk1DPueni+WkqeeahOWwj+eijui0seWXluWXlueahOeni+Wwj+ijpDAw" +
                "MTwvTlNSTUM+CiAgICA8TlNSRFpEQUg+MTUwMDAxNjYxNTY1NzE3MTU0PC9OU1JEWkRBSD4KICAg" +
                "IDxES0JaPjA8L0RLQlo+CiAgICA8S1BYTT7lip7lhaznlKjlk4E8L0tQWE0+CiAgICA8Qk1CX0JC" +
                "SD4xLjA8L0JNQl9CQkg+CiAgICA8WEhGX05TUlNCSD4xNTAwMDE2NjE1NjU3MTcxNTQ8L1hIRl9O" +
                "U1JTQkg+CiAgICA8WEhGTUM+56eL5aSp55qE5bCP56KO6LSx5ZeW5ZeW55qE56eL5bCP6KOkMDAx" +
                "PC9YSEZNQz4KICAgIDxYSEZfRFo+5rGf6IuP55yB5Y2X5Lqs5biC5LiH5rOJ5bqEPC9YSEZfRFo+" +
                "CiAgICA8WEhGX0RIPjAxMC0yMzQ1Njc4OS0xMjM0NTY8L1hIRl9ESD4KICAgIDxYSEZfWUhaSD7n" +
                "gpLkvZzmtIssNjUzMjEzNTY1NjU1NjU2ODwvWEhGX1lIWkg+CiAgICA8R0hGTUM+5Liq5Lq6PC9H" +
                "SEZNQz4KICAgIDxHSEZfTlNSU0JIPjwvR0hGX05TUlNCSD4KICAgIDxHSEZfU0o+MTM1NTg4OTc2" +
                "NTM8L0dIRl9TSj4KICAgIDxHSEZfRU1BSUw+PC9HSEZfRU1BSUw+CiAgICA8R0hGUVlMWD4wMzwv" +
                "R0hGUVlMWD4KICAgIDxHSEZfWUhaSD48L0dIRl9ZSFpIPgogICAgPEhZX0RNPjwvSFlfRE0+CiAg" +
                "ICA8SFlfTUM+PC9IWV9NQz4KICAgIDxLUFk+6YOt5Lic5piOPC9LUFk+CiAgICA8S1BMWD4xPC9L" +
                "UExYPgogICAgPFRTQ0hCWj4wPC9UU0NIQlo+CiAgICA8Q1pETT4xMDwvQ1pETT4KICAgIDxRRF9C" +
                "Wj4wPC9RRF9CWj4KICAgIDxLUEhKSkU+OTgwMC4wMDwvS1BISkpFPgogICAgPEhKQkhTSkU+ODM3" +
                "Ni4wODwvSEpCSFNKRT4KICAgIDxISlNFPjE0MjMuOTI8L0hKU0U+CiAgICA8RlBaTF9ETT41MTwv" +
                "RlBaTF9ETT4KICAgIDxCWj48L0JaPgogIDwvRlBLSlhYX0ZQVFhYPgogIDxGUEtKWFhfWE1YWFMg" +
                "Y2xhc3M9IkZQS0pYWF9YTVhYIiBzaXplPSIzIj4KICAgIDxGUEtKWFhfWE1YWCBjbGFzcz0iRlBL" +
                "SlhYX1hNWFgiPgogICAgICA8WE1NQz7ohbDnvKDkuIfotK88L1hNTUM+CiAgICAgIDxYTURXPuS4" +
                "qjwvWE1EVz4KICAgICAgPEdHWEg+5pegPC9HR1hIPgogICAgICA8WE1TTD4zPC9YTVNMPgogICAg" +
                "ICA8SFNCWj4wPC9IU0JaPgogICAgICA8RlBIWFo+MDwvRlBIWFo+CiAgICAgIDxYTURKPjEwMjUu" +
                "NjQ8L1hNREo+CiAgICAgIDxTUEJNPjEwMjA1MTEwMjAwMDAwMDAwMDA8L1NQQk0+CiAgICAgIDxZ" +
                "SFpDQlM+MDwvWUhaQ0JTPgogICAgICA8WE1KRT4zMDc2LjkyPC9YTUpFPgogICAgICA8U0w+MC4x" +
                "NzwvU0w+CiAgICAgIDxTRT41MjMuMDg8L1NFPgogICAgPC9GUEtKWFhfWE1YWD4KICAgIDxGUEtK" +
                "WFhfWE1YWCBjbGFzcz0iRlBLSlhYX1hNWFgiPgogICAgICA8WE1NQz7mi5votKLnq6XlrZA8L1hN" +
                "TUM+CiAgICAgIDxYTURXPuS4qjwvWE1EVz4KICAgICAgPEdHWEg+5pegPC9HR1hIPgogICAgICA8" +
                "WE1TTD41PC9YTVNMPgogICAgICA8SFNCWj4wPC9IU0JaPgogICAgICA8RlBIWFo+MDwvRlBIWFo+" +
                "CiAgICAgIDxYTURKPjc1Mi4xNDwvWE1ESj4KICAgICAgPFNQQk0+MTAyMDUxMTAyMDAwMDAwMDAw" +
                "MDwvU1BCTT4KICAgICAgPFlIWkNCUz4wPC9ZSFpDQlM+CiAgICAgIDxYTUpFPjM3NjAuNzA8L1hN" +
                "SkU+CiAgICAgIDxTTD4wLjE3PC9TTD4KICAgICAgPFNFPjYzOS4zMDwvU0U+CiAgICA8L0ZQS0pY" +
                "WF9YTVhYPgogICAgPEZQS0pYWF9YTVhYIGNsYXNzPSJGUEtKWFhfWE1YWCI+CiAgICAgIDxYTU1D" +
                "PuexveaWmeW8peWLkuS9mzwvWE1NQz4KICAgICAgPFhNRFc+5LiqPC9YTURXPgogICAgICA8R0dY" +
                "SD7ml6A8L0dHWEg+CiAgICAgIDxYTVNMPjE8L1hNU0w+CiAgICAgIDxIU0JaPjA8L0hTQlo+CiAg" +
                "ICAgIDxGUEhYWj4wPC9GUEhYWj4KICAgICAgPFhNREo+MTUzOC40NjwvWE1ESj4KICAgICAgPFNQ" +
                "Qk0+MTAyMDUxMTAyMDAwMDAwMDAwMDwvU1BCTT4KICAgICAgPFlIWkNCUz4wPC9ZSFpDQlM+CiAg" +
                "ICAgIDxYTUpFPjE1MzguNDY8L1hNSkU+CiAgICAgIDxTTD4wLjE3PC9TTD4KICAgICAgPFNFPjI2" +
                "MS41NDwvU0U+CiAgICA8L0ZQS0pYWF9YTVhYPgogIDwvRlBLSlhYX1hNWFhTPgogIDxGUEtKWFhf" +
                "RERYWCBjbGFzcz0iRlBLSlhYX0REWFgiPgogICAgPERESD43NzQwNjA4MjE3MzYyNTE8L0RESD4K" +
                "ICAgIDxERERBVEU+MjAxNS0xMC0yNCAwOTo0OToxMy4wIFVUQzwvREREQVRFPgogIDwvRlBLSlhY" +
                "X0REWFg+CiAgPEZQS0pYWF9ERE1YWFhTIGNsYXNzPSJGUEtKWFhfRERNWFhYIiBzaXplPSIzIj4K" +
                "ICAgIDxGUEtKWFhfRERNWFhYIGNsYXNzPSJGUEtKWFhfRERNWFhYIj4KICAgICAgPERETUM+6IWw" +
                "57yg5LiH6LSvPC9ERE1DPgogICAgICA8RFc+5LiqPC9EVz4KICAgICAgPEdHWEg+5pegPC9HR1hI" +
                "PgogICAgICA8U0w+MzwvU0w+CiAgICAgIDxESj4xMjAwLjAwPC9ESj4KICAgICAgPEpFPjM2MDAu" +
                "MDA8L0pFPgogICAgPC9GUEtKWFhfRERNWFhYPgogICAgPEZQS0pYWF9ERE1YWFggY2xhc3M9IkZQ" +
                "S0pYWF9ERE1YWFgiPgogICAgICA8RERNQz7mi5votKLnq6XlrZA8L0RETUM+CiAgICAgIDxEVz7k" +
                "uKo8L0RXPgogICAgICA8R0dYSD7ml6A8L0dHWEg+CiAgICAgIDxTTD41PC9TTD4KICAgICAgPERK" +
                "Pjg4MC4wMDwvREo+CiAgICAgIDxKRT40NDAwLjAwPC9KRT4KICAgIDwvRlBLSlhYX0RETVhYWD4K" +
                "ICAgIDxGUEtKWFhfRERNWFhYIGNsYXNzPSJGUEtKWFhfRERNWFhYIj4KICAgICAgPERETUM+57G9" +
                "5paZ5byl5YuS5L2bPC9ERE1DPgogICAgICA8RFc+5LiqPC9EVz4KICAgICAgPEdHWEg+5pegPC9H" +
                "R1hIPgogICAgICA8U0w+MTwvU0w+CiAgICAgIDxESj4xODAwLjAwPC9ESj4KICAgICAgPEpFPjE4" +
                "MDAuMDA8L0pFPgogICAgPC9GUEtKWFhfRERNWFhYPgogIDwvRlBLSlhYX0RETVhYWFM+CiAgPEZQ" +
                "S0pYWF9aRlhYIGNsYXNzPSJGUEtKWFhfWkZYWCIvPgogIDxGUEtKWFhfV0xYWCBjbGFzcz0iRlBL" +
                "SlhYX1dMWFgiLz4KPC9SRVFVRVNUX0ZQS0pYWD4=</content>" +
                "  </data>" +
                "</interface>";
    }
    
    public static String unescapeBackslash(String oldStr){
        //上个字符是否是反斜杠
        boolean saw_backslash = false;
        StringBuilder sb = new StringBuilder(oldStr.length());
        //循环遍历
        char[] charArray = oldStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            //
            if(c=='\\'){
                if(!saw_backslash){
                    saw_backslash=true;
                }else{
                    sb.append(c);
                    saw_backslash=false;
                }
            }else{
                switch (c) {
                    case 't':
                        sb.append("\\t");
                        break;
                    case 'n':
                        sb.append("\\n");
                        break;

                    default:
                        sb.append(c);
                        break;
                }
                saw_backslash=false;
            }
                    
        }
        return sb.toString();
    }
}
