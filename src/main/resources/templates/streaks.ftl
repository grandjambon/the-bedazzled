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
        <h2>Appearance Streaks</h2>
        <p/>
        <table style="border-collapse: collapse; border: 1px solid black;">
            <tr>
                <th width="100">Player</th>
                <th width="100">Num Games</th>
             </tr>
            <#list appStreaks as appStreak>
                <tr>
                    <td bgcolor=${zebra(appStreak_index)} style="border-collapse: collapse; border: 1px solid black;">${appStreak.name}</td>
                    <td bgcolor=${zebra(appStreak_index)} width="50" style="text-align:center; border-collapse: collapse; border: 1px solid black;">${appStreak.total}</td>
                </tr>
            </#list>
        <table>

    </center>
</@u.page>