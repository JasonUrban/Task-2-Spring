function sortArray() {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("games");
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            let dateTimeString1 = rows[i].getElementsByTagName("TD")[0].innerText.split(" ");
            let formattedString1 = dateTimeString1[0].split("-").reverse().join("-") + "T" + dateTimeString1[1];
            let dateTimeString2 = rows[i + 1].getElementsByTagName("TD")[0].innerText.split(" ");
            let formattedString2 = dateTimeString2[0].split("-").reverse().join("-") + "T" + dateTimeString2[1];
            x = new Date(formattedString1);
            y = new Date(formattedString2);
            if (x > y) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}