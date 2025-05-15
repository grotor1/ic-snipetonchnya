import doRequest from '@/api/utils/doRequest';

const useRequest = () => {
  const getRequest = async (url: string, headers?: Record<string, string>) => {
    return await doRequest(url, 'GET', null, headers);
  };

  const postRequest = async (url: string, body?: unknown, headers?: Record<string, string>) => {
    return await doRequest(url, 'POST', body, headers);
  };

  const patchRequest = async (url: string, body?: unknown, headers?: Record<string, string>) => {
    return await doRequest(url, 'PATCH', body, headers);
  };

  const putRequest = async (url: string, body?: unknown, headers?: Record<string, string>) => {
    return await doRequest(url, 'PUT', body, headers);
  };

  const deleteRequest = async (url: string, headers?: Record<string, string>) => {
    return await doRequest(url, 'DELETE', null, headers);
  };

  return {
    get: getRequest,
    post: postRequest,
    patch: patchRequest,
    put: putRequest,
    delete: deleteRequest,
  };
};

export default useRequest;
