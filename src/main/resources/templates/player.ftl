<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />
<@u.page>
        <div>
            <div>
            <center>
                <table>
                    <tr style="font-size:64px">
                        <td style="text-align:center">${name}<td/>
                    <tr>
                </table>
                <table>
                    <tr style="background-color:#d9e5f7">
                        <th width="50" style="text-align:center">P<th>
                        <th width="50" style="text-align:center">W<th>
                        <th width="50" style="text-align:center">D<th>
                        <th width="50" style="text-align:center">L<th>
                        <th width="50" style="text-align:center">GF<th>
                        <th width="50" style="text-align:center">GA<th>
                    </tr>
                    <tr style="background-color:#c3cfe2">
                        <td style="text-align:center">${record.played}<td>
                        <td style="text-align:center">${record.wins}<td>
                        <td style="text-align:center">${record.draws}<td>
                        <td style="text-align:center">${record.losses}<td>
                        <td style="text-align:center">${record.goalsFor}<td>
                        <td style="text-align:center">${record.goalsAgainst}<td>
                    </tr>
                </table>
                <br/>
                <table>
                    <#list playerMatches as playerMatch>
                        <tr style="background-color:#${playerMatch.match.outcome.colour}">
                            <td width="50" style="text-align:center"><a href="<@spring.url '/season/${playerMatch.match.seasonNumber}'/>">${playerMatch.match.seasonNumber}</a></td>
                            <td width="50" style="text-align:center">${playerMatch.match.grade}</td>
                            <td width="200" style="text-align:center">The BeDazzled</td>
                            <td width="50" style="text-align:center"><a href="<@spring.url '/season/${playerMatch.match.seasonNumber}/match/${playerMatch.match.number}'/>">${playerMatch.match.goalsFor} - ${playerMatch.match.goalsAgainst}</a></td>
                            <td width="200" style="text-align:center">${playerMatch.match.opponent}</td>
                            <td width="50" style="text-align:center">${playerMatch.goalsScored}</td>
                        </tr>
                    </#list>
                <table>
            </center>
            </div>
        </div>
</@u.page>