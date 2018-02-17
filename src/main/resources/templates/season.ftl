<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />
<@u.page>
        <div>
            <div>
            <center>
                <b>Season ${season}</b>
                <table>
                    <#list matches as match>
                        <tr style="background-color:#${match.outcome.colour}">
                            <td width="200" style="text-align:center">The BeDazzled</td>
                            <td width="50" style="text-align:center"><a href="<@spring.url '/season/${season}/match/${match.number}'/>">${match.goalsFor} - ${match.goalsAgainst}</a></td>
                            <td width="200" style="text-align:center">${match.opponent}</td>
                        </tr>
                    </#list>
                <table>
            </center>
            </div>
            <center>
                <div>
                    <table style="display:inline-block;vertical-align:top;">
                        <tr>
                            <th style="text-align:center">GK</th>
                        </tr>
                        <#list gks as gk>
                            <tr style="background-color:#e6ffe6">
                                <td width="50" style="text-align:center">${gk.name}</a></td>
                                <td width="50" style="text-align:center">${gk.goalieApps}</td>
                            </tr>
                        </#list>
                    </table>
                    <table style="display:inline-block;vertical-align:top;">
                        <tr>
                            <th style="text-align:center">Apps</th>
                        </tr>
                        <#list apps as app>
                            <tr style="background-color:#e6f7ff">
                                <td width="50" style="text-align:center">${app.name}</a></td>
                                <td width="50" style="text-align:center">${app.apps}</td>
                            </tr>
                        </#list>
                    </table>
                    <table style="display:inline-block;vertical-align:top;">
                        <tr>
                            <th style="text-align:center">Goals</th>
                        </tr>
                        <#list goals as goal>
                            <tr style="background-color:#e6f7ff">
                                <td width="50" style="text-align:center">${goal.name}</a></td>
                                <td width="50" style="text-align:center">${goal.goals}</td>
                            </tr>
                        </#list>
                    </table>
                </div>
            </center>
        </div>
</@u.page>