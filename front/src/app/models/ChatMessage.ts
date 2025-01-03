export class ChatMessage {
    private id : number | null;
    private parentId : number | null;
    private text : string;
    private isRead : boolean | null;
    private senderEmail : string;
    private receiverEmail : string;
    private type : string;

	constructor($text: string, $senderEmail: string, $receiverEmail: string, $type: string, $id: number | null = null, $parentId: number | null = null, $isRead: boolean | null = null) {
		this.id = $id;
		this.parentId = $parentId;
		this.text = $text;
		this.isRead = $isRead;
		this.senderEmail = $senderEmail;
		this.receiverEmail = $receiverEmail;
		this.type = $type;
	}

    /**
     * Getter $id
     * @return {number }
     */
	public get $id(): number | null  {
		return this.id;
	}

    /**
     * Getter $parentId
     * @return {number }
     */
	public get $parentId(): number | null  {
		return this.parentId;
	}

    /**
     * Getter $text
     * @return {string}
     */
	public get $text(): string {
		return this.text;
	}

    /**
     * Getter $isRead
     * @return {boolean }
     */
	public get $isRead(): boolean | null  {
		return this.isRead;
	}

    /**
     * Getter $senderEmail
     * @return {string}
     */
	public get $senderEmail(): string {
		return this.senderEmail;
	}

    /**
     * Getter $receiverEmail
     * @return {string}
     */
	public get $receiverEmail(): string {
		return this.receiverEmail;
	}

    /**
     * Getter $type
     * @return {string}
     */
	public get $type(): string {
		return this.type;
	}

    /**
     * Setter $id
     * @param {number } value
     */
	public set $id(value: number ) {
		this.id = value;
	}

    /**
     * Setter $parentId
     * @param {number } value
     */
	public set $parentId(value: number ) {
		this.parentId = value;
	}

    /**
     * Setter $text
     * @param {string} value
     */
	public set $text(value: string) {
		this.text = value;
	}

    /**
     * Setter $isRead
     * @param {boolean } value
     */
	public set $isRead(value: boolean ) {
		this.isRead = value;
	}

    /**
     * Setter $senderEmail
     * @param {string} value
     */
	public set $senderEmail(value: string) {
		this.senderEmail = value;
	}

    /**
     * Setter $receiverEmail
     * @param {string} value
     */
	public set $receiverEmail(value: string) {
		this.receiverEmail = value;
	}

    /**
     * Setter $type
     * @param {string} value
     */
	public set $type(value: string) {
		this.type = value;
	}

}