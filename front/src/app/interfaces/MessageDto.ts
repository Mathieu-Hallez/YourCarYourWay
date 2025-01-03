export interface MessageDto {
    id : number;
    parent_id : number;
    text : string;
    is_read : boolean;
    sender : string;
    receiver : string;
    created_at : string;
}