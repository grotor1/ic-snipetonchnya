import { BASE_API_URL } from '@/services/state/base';
import { popupState } from '@/services/state/popup';
import { userState } from '@/services/state/user';

async function doRequest(
  path: string,
  method: string = 'GET',
  body: unknown = null,
  headers: Record<string, string> = {},
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
): Promise<any> {
  const url = new URL(BASE_API_URL + path);

  if (headers['Content-Type'] == null) headers['Content-Type'] = 'application/json';
  if (userState.isLoggedIn.value) headers['Authorization'] = 'Bearer ' + userState.getAccessToken.value;
  const initConfig: RequestInit = {
    method: method,
    headers: headers,
  };

  if (body != null) initConfig['body'] = JSON.stringify(body);

  const res = await fetch(url, initConfig);
  if (res.status === 204) {
    return;
  }
  try {
    const data = await res.json();
    if (res.ok) {
      return data ?? true;
    } else {
      popupState.newPopup(data?.message || data?.error || 'Что-то не так');
    }
  } catch (e) {}
}

export default doRequest;
