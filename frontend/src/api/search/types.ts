import type {IId, TId} from "@/api/default/types";
import type {ITech} from "@/api/tech/types";
import type {ITag} from "@/api/tag/types";

export interface ISearch extends IId {
  name: string;
  titleEntry: string;
  techs: ITech[];
  tags: ITag[];
  createdAt: string;
}
