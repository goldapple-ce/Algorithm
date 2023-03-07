def solution(tickets):
    answer = []
    global visited
    visited = [False] * len(tickets)
    tickets.sort()
    starts = select('ICN',tickets)
    
    def dfsTickets(idx,routes):
        if not False in visited:

            return routes+[tickets[idx][1]]
        starts = select(tickets[idx][1],tickets)
        for start in starts:
            visited[start] = True
            n_routes = dfsTickets(start,routes+[tickets[start][0]])
            if n_routes and len(n_routes) == len(tickets)+1:
                return n_routes
            visited[start] = False
    
    routes = []
    for start in starts:
        visited[start] = True
        routes = dfsTickets(start,['ICN'])
        if routes and len(routes) == len(tickets)+1:
            return routes
        visited[start] = False

    return 


def select(target,tickets):
    global visited
    starts = []
    for i in range(len(tickets)):
        if tickets[i][0] == target and not visited[i]:
            starts.append(i)
    return starts