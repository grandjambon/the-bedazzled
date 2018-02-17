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
        <p/>
        <h2>All Goalie Appearances</h2>
        <p/>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Goalie</th>
                <th width="100">Apps</th>
             </tr>
            <#list goalieApps as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goalieApps}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>All Outfield Appearances</h2>
        <p/>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Apps</th>
             </tr>
            <#list apps as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.apps}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>A Grade Outfield Appearances</h2>
        <p/>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Apps</th>
             </tr>
            <#list aGradeApps as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.apps}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>B Grade Outfield Appearances</h2>
        <p/>

        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Apps</th>
             </tr>
            <#list bGradeApps as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.apps}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>All Goals</h2>
        <p/>

        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Goals</th>
             </tr>
            <#list goals as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goals}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>A Grade Goals</h2>
        <p/>

        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Goals</th>
             </tr>
            <#list aGradeGoals as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goals}</td>
                </tr>
            </#list>
        <table>

        <p/>
        <h2>B Grade Goals</h2>
        <p/>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Goals</th>
             </tr>
            <#list bGradeGoals as player>
                <tr>
                    <td bgcolor=${zebra(player_index)} style="border-collapse: collapse; border: 1px solid black;">${player.name}</td>
                    <td bgcolor=${zebra(player_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${player.goals}</td>
                </tr>
            </#list>
        <table>

    </center>
</@u.page>