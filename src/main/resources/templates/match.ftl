<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />
<@u.page>
    <p>
    <center><a href='<@spring.url "/season/${match.season}"/>'>Season ${match.season}</a></center>
    <p>
    <center>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <td colspan="3" style="text-align:center">${match.header}</td>
            </tr>
            <tr>
                <td colspan="3"> </td>
            </tr>
            <#list match.firstHalf as event>
                <tr>
                    <td width="50"></td>
                    <td width="35" style="text-align:center">${event.score}</td>
                    <td width="75"style="text-align:center">${event.scorer}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="3"> </td>
            </tr>
            <tr>
                <td colspan="3"style="text-align:center">${match.halfTimeScore}</td>
            </tr>
            <tr>
                <td colspan="3"> </td>
            </tr>
            <#list match.secondHalf as event>
                <tr>
                    <td width="50"></td>
                    <td width="35" style="text-align:center">${event.score}</td>
                    <td width="75" style="text-align:center">${event.scorer}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="3"> </td>
            </tr>
            <tr>
                <td colspan="3" style="text-align:center">${match.finalScore}</td>
            </tr>
        </table>
        </br>
        <#if !match.isForfeit>
            </br>
            <table style="display:inline-block;vertical-align:top;">
                <tr style="background-color:#e6ffe6">
                    <td width="50" style="text-align:center"><a href="<@spring.url '/player/${goalie}'/>">${goalie}</a></td>
                </tr>
                <#list outfielders as outfielder>
                    <tr style="background-color:#e6f7ff">
                        <td width="50" style="text-align:center"><a href="<@spring.url '/player/${outfielder}'/>">${outfielder}</a></td>
                    </tr>
                </#list>
            </table>
        </#if>
    </center>
</@u.page>