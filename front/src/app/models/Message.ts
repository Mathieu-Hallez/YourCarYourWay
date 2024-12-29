export class Message {
    private id : number;
    private parentId : number;
    private content : string;
    private isRead : boolean;
    private sender : string;
    private receiver : string;
    private createdAt : string;

	constructor($id: number, $parentId: number, $content: string, $isRead: boolean, $sender: string, $receiver: string, $createdAt: string) {
		this.id = $id;
		this.parentId = $parentId;
		this.content = $content;
		this.isRead = $isRead;
		this.sender = $sender;
		this.receiver = $receiver;
		this.createdAt = $createdAt;
	}

    /**
     * Getter $id
     * @return {number}
     */
	public get $id(): number {
		return this.id;
	}

    /**
     * Getter $parentId
     * @return {number}
     */
	public get $parentId(): number {
		return this.parentId;
	}

    /**
     * Getter $content
     * @return {string}
     */
	public get $content(): string {
		return this.content;
	}

    /**
     * Getter $isRead
     * @return {boolean}
     */
	public get $isRead(): boolean {
		return this.isRead;
	}

    /**
     * Getter $sender
     * @return {string}
     */
	public get $sender(): string {
		return this.sender;
	}

    /**
     * Getter $receiver
     * @return {string}
     */
	public get $receiver(): string {
		return this.receiver;
	}

    /**
     * Getter $createdAt
     * @return {string}
     */
	public get $createdAt(): string {
		return this.createdAt;
	}

    /**
     * Setter $id
     * @param {number} value
     */
	public set $id(value: number) {
		this.id = value;
	}

    /**
     * Setter $parentId
     * @param {number} value
     */
	public set $parentId(value: number) {
		this.parentId = value;
	}

    /**
     * Setter $content
     * @param {string} value
     */
	public set $content(value: string) {
		this.content = value;
	}

    /**
     * Setter $isRead
     * @param {boolean} value
     */
	public set $isRead(value: boolean) {
		this.isRead = value;
	}

    /**
     * Setter $sender
     * @param {string} value
     */
	public set $sender(value: string) {
		this.sender = value;
	}

    /**
     * Setter $receiver
     * @param {string} value
     */
	public set $receiver(value: string) {
		this.receiver = value;
	}

    /**
     * Setter $createdAt
     * @param {string} value
     */
	public set $createdAt(value: string) {
		this.createdAt = value;
	}

}