
export const getParamsFromObject = (obj: Record<string, string[] | string | number | undefined>) => {
  let str = [];
  for (let p in obj)
    if (obj.hasOwnProperty(p) && obj[p]) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]?.toString() ?? ''));
    }
  return str.join("&");
}
