<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>
    <center>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th>Season</th>
                <th>Grade</th>
                <th>P</th>
                <th>W</th>
                <th>D</th>
                <th>L</th>
                <th>F</th>
                <th>A</th>
                <th>GD</th>
                <th>Honours</th>
             </tr>
            <#list seasons as season>
                <tr style="background-color:#${season.grade.color}">
                    <td style="text-align:center"><a href="season/${season.number}">${season.number}</a></td>
                    <td style="text-align:center">${season.grade}</td>
                    <td width="50" style="text-align:center">${season.numGames}</td>
                    <td width="50" style="text-align:center">${season.wins}</td>
                    <td width="50" style="text-align:center">${season.draws}</td>
                    <td width="50" style="text-align:center">${season.losses}</td>
                    <td width="50" style="text-align:center">${season.goalsFor}</td>
                    <td width="50" style="text-align:center">${season.goalsAgainst}</td>
                    <td width="50" style="text-align:center">${season.goalDifference}</td>
                    <td style="text-align:center">${season.honours}</td>
                </tr>
            </#list>
        <table>
    </center>
</@u.page>