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
                <table style="border-collapse: collapse; border: 1px solid black; table-layout: fixed">
                    <tr>
                        <th style="border-collapse: collapse; border: 1px solid black; text-align:center" width=20%>Player</th>
                        <!--th style="text-align:center">EarlierDebt</th-->
                        <th style="border-collapse: collapse; border: 1px solid black; text-align:center" width=20%>Season ${lastFullSeason}</th>
                        <th style="border-collapse: collapse; border: 1px solid black; text-align:center" width=20%>Debt</th>
                        <th style="border-collapse: collapse; border: 1px solid black; text-align:center" width=20%>Season ${currentSeason}</th>
                        <th style="border-collapse: collapse; border: 1px solid black; text-align:center" width=20%>Real Time Debt</th>
                    </tr>
                    <#list accounts?keys as key>
                        <tr>
                            <td bgcolor=${zebra(key_index)} style="border-collapse: collapse; border: 1px solid black; text-align:center">${key}</td>
                            <!--td style="text-align:center">${accounts[key].earlierDebt}</td-->
                            <td bgcolor=${zebra(key_index)} style="border-collapse: collapse; border: 1px solid black; text-align:center">${accounts[key].lastFullSeasonCost}</td>
                            <td bgcolor=${zebra(key_index)} style="border-collapse: collapse; border: 1px solid black; text-align:center">${accounts[key].debtAsEndOfLastSeason}</td>
                            <td bgcolor=${zebra(key_index)} style="border-collapse: collapse; border: 1px solid black; text-align:center">${accounts[key].thisSeasonCost}</td>
                            <td bgcolor=${zebra(key_index)} style="border-collapse: collapse; border: 1px solid black; text-align:center">${accounts[key].liveDebt}</td>
                        </tr>
                    </#list>
                </table>
            </center>
            </div>
         </div>
</@u.page>
