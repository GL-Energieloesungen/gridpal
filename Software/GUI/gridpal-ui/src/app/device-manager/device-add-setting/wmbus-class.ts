export class WmbusClass {

    getManufacturerList(inputObject: Object[], searchStr: string): Object[] {
        const outputObj: Object[] = [];
        inputObject.forEach((inputObjElement, index) => {
            if (outputObj.indexOf(inputObjElement[searchStr]) !== 0) {
                outputObj[index] = inputObjElement[searchStr];
            }
        });
        return outputObj;
    }

    getDeviceList(inputObject: Object[], manufacturerName: String): Object[] {
        const outputObj: Object[] = [];
        inputObject.forEach((element, index) => {
            if (element['manufacturer'] === manufacturerName) {
                outputObj[index] = element['devicetype'] + ' (' + element['id'] + ')';
            }
        });
        return outputObj;
    }

    getConnectionRegister(inputObject: Object[],
        manufacturerName: String,
        devicetype: String,
        deviceID: String) {
        const outputObj: Object[] = [];

        inputObject.forEach(element => {
            if (element['manufacturer'] === manufacturerName &&
                element['devicetype'] === devicetype &&
                element['id'] === deviceID) {
                    outputObj['connection'] = element['connection'];
                    outputObj['register-map'] = element['register-map'];
            }
        });

        return outputObj;        
    }
}
