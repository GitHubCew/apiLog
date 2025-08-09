# API-Log Documentation
API-Log is a web-based command-line tool for interface monitoring, developed with SpringBoot and WebSocket. It enables real-time monitoring of one or multiple API interfaces, tracking input parameters, return values, and execution time, addressing challenges in complex scenarios where traditional monitoring falls short.


# Key Features
- Real-time monitoring via WebSocket connections
- Support for monitoring multiple interfaces with fuzzy search capability
- Tracks input parameters, return values, and execution time
- Allows removal of monitored interfaces
- High performance with minimal impact on original business operations
- Compatible with various clients including web terminals and WebSocket tools

# Usage
1.Clone the repository:
```shell
git clone
```

2.Install to local Maven repository:
```shell
mvn clean install
```

Alternatively, pull the latest dependency from Maven Central:
[Maven Central Repository (Sonatype)](https://central.sonatype.com/artifact/io.github.githubcew/api-log/)

3.Add dependency to your project:
```xml
<dependency>
    <groupId>io.github.githubcew</groupId>
    <artifactId>api-log</artifactId>
    <version>${version}</version> <!-- Replace with actual version -->
</dependency>
```

4.Security Configuration
  
If your project has security validation, whitelist these paths:

- /alog-ws
- /alog/alog-terminal.html

Example for Shiro:
```java
filters.put("/alog-ws", "anon");
filters.put("/alog/alog-terminal.html", "anon");
```
Example for Spring Security:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/alog-ws", "/alog/alog-terminal.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable();

```

5.Getting Started
   
- Start your application
- Access the web interface at:

```shell
[your-domain]/[context-path]/alog/alog-terminal.html
```

**Example**: localhost:8080/myapp/alog/alog-terminal.html

**Upon successful connection, you'll see**:

```shell
┌──────────────────────────────┐
 │   Welcome to ALog Terminal   │
 │      Author: chenenwei       │
 └──────────────────────────────┘

     Type 'help' for command list
 
 $
```

# Command Reference
**connect** - Establish WebSocket connection

**exit/qui**t： Terminate WebSocket connection

**clear**： Clear terminal screen

**help**： Display help information

**ls** [path]： List API interfaces (supports fuzzy search)

**monitor** [path] [param | result | time]： Monitor specific API (options: param, result, time)

**remove** [path]：Remove monitoring for specified API

**clearall**： Clear all monitored APIs

**Tip**: Use ↑/↓ arrow keys to navigate command history

# Example Usage

Consider this sample API:
```java
@GetMapping("/activityWalkRouteActivity/info")
public Result<ActivityWalkRouteActivityInfoVO> info(@RequestParam("id") Long id) {
    // Implementation
}

```

To monitor input parameters and execution time for /activityWalkRouteActivity/info:

1.Connect to the terminal and establish WebSocket connection:
```shell
$ connect
✅ WebSocket connected
```

2.Start monitoring:
```shell
alog> monitor /activityWalkRouteActivity/info param,time
success
```
3.Trigger the API (e.g., via curl):
```shell
curl http://localhost:8080/activityWalkRouteActivity/info?id=495
```

4.View monitoring output:
```text
>monitor /activityWalkRouteActivity/info param,time
success

"id":495   # Method parameter
151        # Execution time (ms)
```