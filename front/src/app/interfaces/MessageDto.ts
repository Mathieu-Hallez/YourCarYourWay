export interface MessageDto {
    id : number;
    parentId : number;
    content : string;
    isRead : boolean;
    sender : string;
    receiver : string;
    createdAt : string;
}