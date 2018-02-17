<!DOCTYPE html>

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
    <center>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;">All Seasons</th>
                <th bgcolor="e6fffa" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;">A Grade</th>
                <th bgcolor="ffffe6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;">B Grade</th>
                <th bgcolor="ffe6e6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;">Other</th>
            </tr>
            <tr>
                <td width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/all/minapps/0'/>">All</a></td>
                <td bgcolor="e6fffa" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/a/minapps/0'/>">All</a></td>
                <td bgcolor="ffffe6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/b/minapps/0'/>">All</a></td>
                <td bgcolor="ffe6e6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/t/minapps/0'/>">Transition</a></td>
            </tr>
            <tr>
                <td width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/all/minapps/10'/>">10+ Apps</a></td>
                <td bgcolor="e6fffa" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/a/minapps/10'/>">10+ Apps</a></td>
                <td bgcolor="ffffe6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/appearances/grade/b/minapps/10'/>">10+ Apps</a></td>
                <td bgcolor="ffe6e6" width="150" style="text-align:center; border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/foundingfathers'/>">Founding Fathers</a></td>
            </tr>
        </table>

        <p/>

        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th>Player</th>
                <th>Goalie Apps</th>
                <th>Outfield Apps</th>
                <th>Goals</th>
                <th>Wins</th>
                <th>Draws</th>
                <th>Losses</th>
                <th>PPG</th>
             </tr>
            <#list players as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;"><a href="<@spring.url '/player/${player.name}'/>">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goalieApps}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.apps}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goals}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.wins}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.draws}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.losses}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.ppg}</td>
                </tr>
            </#list>
        <table>
    </center>
</@u.page>