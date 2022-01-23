import 'rxjs/add/operator/map';
import { DateOperation } from './date';

export class ItemDataCheck {

    dateObj = new DateOperation();

    tempTableData(groupList: string[], apiData: Object[]): Object[] {
        const tempObj = groupList.map(x => {
            const groupObjectList: Object[] = [];
            let bar;
            apiData.forEach(element => {
                if (x === element['groupNames'][0]) {
                    if ('transformedState' in element) {
                        bar = element['transformedState'];
                    } else {
                        bar = element['state'];
                    }
                    groupObjectList.push({
                        time: this.dateObj.currentTime(),
                        original: element['name'],
                        name: element['name'].split('__')[1].split('_').join(' '),
                        value: this.transformedState(bar),
                        state: element['state']
                    });
                }
            });
            return ({ [x]: groupObjectList });
        });
        return tempObj;
    }

    transformedState(str: string): String {
        if (str === 'NULL') {
            return '';
        }
        let a: string;
        let b: string[];
        try {
            b = str.split(' ');
            if (b.length > 1) {
                if (this.isNumeric(b[0])) {
                    a = [parseFloat(b[0]).toFixed(4), b[1]].join(' ');
                } else if (this.isNumeric(b[1])) {
                    a = [parseFloat(b[1]).toFixed(4), b[0]].join(' ');
                }
            } else {
                if (this.isNumeric(b[0])) {
                    a = parseFloat(b[0]).toFixed(4);
                } else {
                    a = b[0];
                }
            }
        } catch (error) {
            if (this.isNumeric(str)) {
                a = parseFloat(str).toFixed(4);
            } else {

                a = str;
            }
        }

        return a;
    }

    isNumeric(n): Boolean {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    getGroupList(apiData: Object[]): string[] {
        const groupList: string[] = [];
        apiData.forEach(element => {
            const groupName = element['groupNames'][0];
            if (!groupList.includes(groupName)) {
                groupList.push(groupName);
            }
        });
        return groupList;
    }

    newTabledata(tempTable: Object[], pastTable: Object[]): Object[] {
        // this will compare new and old data set
        let newTableData: Object[] = [];
        if (pastTable.length !== 0) {

            for (const iterator of tempTable) {
                const tempName = Object.keys(iterator)[0];
                const tempObj = Object.values(iterator)[0];
                pastTable.forEach(element => {
                    const pastName = Object.keys(element)[0];
                    if (tempName === pastName) {
                        const pastValue = Object.values(element)[0];
                        pastValue.forEach(element2 => {
                            tempObj.forEach(element3 => {
                                if (element2['name'] === element3['name']) {
                                    if (element2['state'] !== element3['state']) {
                                        element3['time'] = this.dateObj.currentTime();
                                    } else {
                                        element3['time'] = element2['time'];

                                    }
                                }
                            });

                        });

                    }
                });
            }
            newTableData = tempTable;
        } else {
            newTableData = tempTable;
        }
        return newTableData;

    }
}
