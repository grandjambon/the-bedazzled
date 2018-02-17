<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>
    <center>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th>Opponent</th>
                <th>P</th>
                <th>W</th>
                <th>D</th>
                <th>L</th>
                <th>F</th>
                <th>A</th>
             </tr>
            <#list opponents as opponent>
                <tr style="background-color:#e6f5ff">
                    <td style="text-align:center"><a href="opponent/${opponent.opponent}">${opponent.opponent}</a></td>
                    <td width="50" style="text-align:center">${opponent.played}</td>
                    <td width="50" style="text-align:center">${opponent.wins}</td>
                    <td width="50" style="text-align:center">${opponent.draws}</td>
                    <td width="50" style="text-align:center">${opponent.losses}</td>
                    <td width="50" style="text-align:center">${opponent.goalsFor}</td>
                    <td width="50" style="text-align:center">${opponent.goalsAgainst}</td>
                </tr>
            </#list>
        <table>
    </center>
</@u.page>