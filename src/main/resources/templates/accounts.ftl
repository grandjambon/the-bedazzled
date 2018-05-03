<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />
<@u.page>
    <#function zebra index>
      <#if (index % 2) == 0>
        <#return "white" />
      <#else>
        <#return "#efefef" />
      </#if>
    </#function>

        <div>
            <div>
            <center>
                <table style="border-collapse: collapse; border: 1px solid black;">
                    <tr>
                        <th style="text-align:center">Player</th>
                        <th style="text-align:center">Debt/Credit</th>
                    </tr>
                    <#list accounts?keys as key>
                        <tr>
                            <td style="text-align:center">${key}</td>
                            <td style="text-align:center">${accounts[key].debt}</td>
                        </tr>
                    </#list>
                </table>
            </center>
            </div>
         </div>
</@u.page>
