<#macro page>
    <#import "/spring.ftl" as spring />
    <html lang="en">
        <head>
            <link rel="stylesheet" type="text/css" href="<@spring.url '/bedazzled.css'/>"/>
        </head>
        <body>
            <div class="flex-container"/>
                <header><p class="monospace">The BeDazzled</a></header>

                <nav class="nav">
                <center>
                    <ul>
                        <li><a href="<@spring.url '/'/>">Home</li>
                        <li><a href="<@spring.url '/history'/>">All Time Match History</a></li>
                        <li><a href="<@spring.url '/appearances/grade/all/minapps/0'/>">All Player Stats</a></li>
                        <li><a href="<@spring.url '/opponents'/>">Opponents</a></li>
                        <li><a href="<@spring.url '/records'/>">Records</a></li>
                        <li><a href="<@spring.url '/accounts'/>">Accounts</a></li>
                    </ul>
                </center>
                </nav>
                <article class="article">
                    <#nested>
                </article>

             </div>
             <footer><center><p class="monospace">Pardon, old father, my mistaking eyes, that have been so BeDazzled with the sun that everything I look on seemeth green.</p></center></footer>
        </body>
    </html>
</#macro>