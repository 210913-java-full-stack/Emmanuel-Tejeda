

$.get('http://localhost:8080/ServeletTest/cr', function(data) {
            // console.log(data);
            servStartingCity = data.startingCity;

            for (var i = 0; i <data.length; i++) {  

            let myTable = document.querySelector('#table');

            let employees = [
                { flightID: data[i].flightID , startingCity: data[i].startingCity, 
                    cityOfArrival: data[i].landingCity},
            
            ]

            let headers = ['flight ID', 'Starting City', "City of arrival"];

            

            let table = document.createElement('table');
            let headerRow = document.createElement('tr');
            headers.forEach(headerText => {
                let header = document.createElement('th');
                let textNode = document.createTextNode(headerText);
                header.appendChild(textNode);
                headerRow.appendChild(header);
            });

            table.appendChild(headerRow);
            employees.forEach(emp => {
                let row = document.createElement('tr');
                Object.values(emp).forEach(text => {
                    let cell = document.createElement('td');
                    let textNode = document.createTextNode(text);
                    cell.appendChild(textNode);
                    row.appendChild(cell);
                })
                table.appendChild(row);
            });
            myTable.appendChild(table);
        }




});

