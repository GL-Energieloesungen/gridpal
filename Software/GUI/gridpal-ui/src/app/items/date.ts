import { DatePipe } from '@angular/common';

export class DateOperation {
    currentTime(): String {
        const formattedDate = new DatePipe('en-US').transform(Date.now(), 'yyyy-MM-ddTHH:mm:ss');
        return formattedDate;
    }
}
